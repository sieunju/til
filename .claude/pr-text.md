## 🔨 Refactor

### 🎯 리팩토링 목적
- [x] 코드 가독성 향상
- [ ] 성능 개선
- [x] 코드 중복 제거
- [x] 아키텍처 개선
- [ ] 테스트 용이성 개선
- [ ] 기타:

### 📝 주요 변경사항

**1. JSend response format payload 필드 제거**
- `JSendList`, `JSendListWithMeta`의 중첩 구조(`data.payload`) 제거 → `list`, `meta` 최상위 필드로 단순화
- `JSendObj`, `JSendObjWithMeta`, `JSendFlatConverterFactory` 클래스 삭제
- `AuthManagerImpl` 토큰 파싱 로직: `data.payload` 중간 파싱 7줄 → 직접 역직렬화 1줄로 축약
- `BaseJSend.isValid` 기본값 `false` → `get() = isSuccess` 로 실제 상태 반영

**2. 데이터 모델 네이밍 규칙 통일**
- **API 응답 모델**: `*Entity` → `*DTO` (core 3개 + feature 23개, 총 26개 파일)
  - `AuthTokenEntity` → `AuthTokenDTO`, `MetaEntity` → `MetaDTO`, `EmptyEntity` → `EmptyDTO`
  - `GoodsEntity` → `GoodsDTO` (8개 feature 모듈)
  - `FileEntity` → `FileDTO`, `MemoEntity` → `MemoDTO`, `JSendTestEntity` → `JSendTestDTO`
  - `JSendEntity` → `JSendDTO`, `JwtTokenTestEntity` → `JwtTokenTestDTO`, `LikeEntity` → `LikeDTO`
  - `CustomMetaEntity` → `CustomMetaDTO` (4개 feature 모듈)
- **DB 모델**: `core/local/models/GoodsEntity` (`@Entity`) 유지
- **Domain 모델**: `GoodsModel` → `Goods`, `MemoModel` → `Memo` (접미사 제거, 8개 모듈)

**3. Compose Status Bar Inset 처리 통일**
- 전체 Compose Activity에 `enableEdgeToEdge()` + `windowInsetsPadding(WindowInsets.statusBars)` 적용
  - `ComposeNavigationActivity`, `GeneralComposeActivity`, `MemoComposeUiActivity`, `ComposePermissionsResultActivity`, `PermissionScreen`
- `RoomObserverActivity`: `ViewCompat.setOnApplyWindowInsetsListener` View 방식 제거 → Compose `windowInsetsPadding` 방식으로 교체

### 📈 개선 효과

**네이밍 규칙 통일 (Before/After)**
```
Before                          After
─────────────────────────────────────────────────
GoodsEntity (@Serializable)  →  GoodsDTO         ← API 응답
GoodsEntity (@Entity Room)   →  GoodsEntity      ← DB 모델 (유지)
GoodsModel                   →  Goods            ← Domain 모델
```
- 클래스명만으로 레이어 역할 구분 가능, 혼동 여지 제거
- 101개 파일, 총 -415/+478줄 변경

**JSend 구조 단순화 (Before/After)**
```json
// Before
{ "data": { "payload": [...] } }

// After
{ "list": [...] }
```
- 불필요한 중첩 래퍼 클래스(`JSendObj`, `JSendObjWithMeta`) 73줄 삭제
- 토큰 파싱 로직 7줄 → 1줄

**Status Bar Inset (Before/After)**
```kotlin
// Before (RoomObserverActivity)
ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { v, insets ->
    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
    v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
    insets
}

// After (모든 Compose Activity 동일 방식)
Surface(
    modifier = Modifier
        .fillMaxSize()
        .windowInsetsPadding(WindowInsets.statusBars)
)
```

### 🔄 마이그레이션 가이드

- `JSendObj<T>` → API 반환 타입을 `T` 로 직접 변경
- `JSendList<T>` JSON 키: `data.payload` → `list` (서버 스펙 동반 변경)
- `JSendListWithMeta<T, M>` JSON 키: `data.payload` / `data.meta` → `list` / `meta` (서버 스펙 동반 변경)
- `*Entity` (API 모델) import 경로: 클래스명 `*DTO` 로 변경 필요
- `GoodsModel` / `MemoModel` → `Goods` / `Memo` 로 변경 필요

### 🐵 Etc.
