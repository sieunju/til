## 🔨 Refactor

### 🎯 리팩토링 목적
- [x] 코드 가독성 향상
- [ ] 성능 개선
- [ ] 코드 중복 제거
- [x] 아키텍처 개선
- [ ] 테스트 용이성 개선
- [ ] 기타:

### 📝 주요 변경사항

**1. Navigator 코루틴 제거 및 동기화 전환**

- `Navigator.navigate()`: 반환값 `RouterResult` 제거 → `Unit` 반환
- `Router.execute()`: `suspend fun` → 일반 `fun` 전환
- `NavigatorImpl`: `GlobalScope.launch` + `withContext(Dispatchers.Main)` 제거 → 단순 반복문으로 대체
- 불필요한 코루틴 의존성(`GlobalScope`, `Dispatchers`, `withContext`) 전부 삭제

**2. Navigator ActivityResult 지원 확장**

- `Navigator.navigateForResult(context, uri, ActivityResultLauncher<Intent>)` 추가
- `Router.isActivityResult(): Boolean` 플래그 추가 (기본값 `false`)
- `Router.executeForResult(context, path, params, launcher)` open fun 추가
- `NavigatorImpl.navigate()`: `isActivityResult() = true` 라우터 skip
- `NavigatorImpl.navigateForResult()`: `isActivityResult() = true` 라우터만 처리

**3. WebBridge 기반 클래스 추가 (core-navigator)**

- `WebAction`: JavascriptInterface action key 열거형
- `WebActionResult`: `Callback(dataMap)` / `Skip` sealed interface
- `WebActionRouter`: `execute(WorkerThread)` + `completeCallback(MainThread)` 추상 클래스

**4. activity_result 모듈 신규 추가**

- `ActivityResultLauncher` 기반 화면 전환 예제 (`result_callback/` 패키지)
- WebBridge 예제: 웹→앱 JavascriptInterface 통신 (`webbridge/` 패키지)
  - `WebBridgeActionCommand`: `Channel<Pair<Router, Callback>>`(WorkerThread→MainThread) + `PublishSubject<ActivityResult>`(RxJava EventBus)
  - `EditTextWebActionRouter`: WorkerThread `blockingFirst()` 블로킹 + 5초 timeout
  - `AlertWebActionRouter`: 네이티브 AlertDialog → `evaluateJavascript` 콜백

### 📈 개선 효과

**Before**
```kotlin
// 코루틴 기반 — GlobalScope 남용, 불필요한 스레드 전환
override fun navigate(context: Context, uri: Uri): RouterResult {
    GlobalScope.launch(Dispatchers.IO) {
        val result = withContext(Dispatchers.Main) { router.execute(...) }
    }
}

// suspend 함수 — 모든 Router 구현체에 코루틴 강제
abstract suspend fun execute(context: Context, ...): RouterResult
```

**After**
```kotlin
// 단순 동기 반복 — 스레드 컨텍스트 명확
override fun navigate(context: Context, uri: Uri) {
    for (router in processors) {
        if (router.isActivityResult()) continue
        val result = router.execute(context, path, uri.toQueryMap())
        if (result is RouterResult.Success) return
    }
}

// 일반 함수 — 구현체 단순화
abstract fun execute(context: Context, ...): RouterResult
```

- `navigate()` / `navigateForResult()` 분리로 일반 화면 전환과 ActivityResult 화면 전환의 책임이 명확히 구분됨
- `ActivityResultLauncher` 등록 책임을 호출부(Activity/Fragment)에 위임 → 라이프사이클 안전

### 🔄 마이그레이션 가이드

**Router 구현체 수정 필요**
```kotlin
// Before
override suspend fun execute(context: Context, path: String, params: Map<String, String>): RouterResult

// After
override fun execute(context: Context, path: String, params: Map<String, String>): RouterResult
```

**navigate() 반환값 제거**
```kotlin
// Before
val result = navigator.navigate(context, uri)

// After
navigator.navigate(context, uri)  // Unit 반환
```

**ActivityResult 화면 전환 방법**
```kotlin
// Activity/Fragment onCreate에서 런처 등록
val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result -> ... }

// 필요할 때 navigate
navigator.navigateForResult(this, Route.XXX.getUri(), launcher)

// Router 구현체에서 isActivityResult() override 필수
override fun isActivityResult(): Boolean = true
override fun executeForResult(context, path, params, launcher): RouterResult {
    launcher.launch(Intent(context, TargetActivity::class.java))
    return RouterResult.Success()
}
```

### 🐵 Etc.

**WebBridge EditTextWebActionRouter 동시 호출 주의**

`PublishSubject`는 멀티캐스트 특성상, 동시에 두 개의 `execute()`가 블로킹 대기 중일 때 하나의 `ActivityResult`가 두 스레드 모두를 unblock할 수 있습니다.

현재 예제 수준에서는 Activity가 떠 있는 동안 중복 호출이 불가하므로 실질적 문제 없음. 방어가 필요하다면 `Executors.newSingleThreadExecutor()` 사용 권장.
