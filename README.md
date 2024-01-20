# TIL(Today I Learned) Wiki

## ✔ 프로젝트 구성
- 멀티 모듈 방식 기본적인 buildSrc, data, domain, model, presentation 로 구성되어 있으며
필요에 따라 모듈이 추가 될 수 있습니다.
- [AS-IS API 문서](https://node.qtzz.synology.me/api-docs/#/TIL) 여기는 죽었어요 😭
- [TO-BE API 문서](https://til.qtzz.synology.me/swagger-ui/index.html) 슬슬 여기로 이전될 예정입니다. 🐶

## ✔ 버전
- ![min23](https://img.shields.io/badge/minSDK-23-blue.svg)  ![target33](https://img.shields.io/badge/targetSDK-33-red.svg)
- ![kotlin 1.8.0](https://img.shields.io/badge/Kotlin/1.8.0-7F52FF?style=flat-square&logo=Kotlin&logoColor=white)
- ![Hilt 2.45](https://img.shields.io/badge/Hilt-2.45-red.svg)

## ✔ 스펙
- Multi Module
- Dependency Inject
    - Hilt
- HTTP Network
    - OkHttp3 & Retrofit
- 비동기
    - Reactive X
    - Coroutine

## ✔ 작성규칙
- 공부한 내용 및 모듈의 링크는 여기에 업데이트 합니다. 자세한 설명 같은 경우 wiki에 업데이트 합니다.
- 공부하거나 고민한 내용에 대한 소스는 'module' or 'package' 단위로 합니다.
- 1일 1 잔디를 목표로 합니다.... 🙏

## ✔ 공부한 내용
- 네트워크 통신
    - [토큰 만료 시 재인증 하는 방법](https://github.com/sieunju/TIL/wiki/%ED%86%A0%ED%81%B0-%EB%A7%8C%EB%A3%8C-%EC%8B%9C-%EC%9E%AC%EC%9D%B8%EC%A6%9D-%ED%95%98%EB%8A%94-%EB%B0%A9%EB%B2%95)
    - [JSON jsend 규칙으로 데이터 모델 구성해보기](https://github.com/sieunju/TIL/wiki/JSON-jsend-%EA%B7%9C%EC%B9%99%EC%9C%BC%EB%A1%9C-%EB%8D%B0%EC%9D%B4%ED%84%B0-%EB%AA%A8%EB%8D%B8-%EA%B5%AC%EC%84%B1%ED%95%B4%EB%B3%B4%EA%B8%B0)
    - [JSend Data Model 에서 Depth 지옥에서 벗어나보기](https://github.com/sieunju/TIL/wiki/JSend-Data-Model-%EC%97%90%EC%84%9C-Depth-%EC%A7%80%EC%98%A5%EC%97%90%EC%84%9C-%EB%B2%97%EC%96%B4%EB%82%98%EB%B3%B4%EA%B8%B0)
    - [네트워크 통신 코루틴으로 처리해보기](https://github.com/sieunju/TIL/wiki/%EB%84%A4%ED%8A%B8%EC%9B%8C%ED%81%AC-%ED%86%B5%EC%8B%A0-%EC%BD%94%EB%A3%A8%ED%8B%B4%EC%9C%BC%EB%A1%9C-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0)
    - [JWT 토큰 재발급 효율적으로 처리해보기](https://github.com/sieunju/TIL/wiki/JWT-%ED%86%A0%ED%81%B0-%EC%9E%AC%EB%B0%9C%EA%B8%89-%ED%9A%A8%EC%9C%A8%EC%A0%81%EC%9C%BC%EB%A1%9C-%EC%B2%98%EB%A6%AC%ED%95%98%EA%B8%B0-V2)

- RecyclerView
    - [Controller 와 ViewHolder 간의 의존성 제거해보기](https://github.com/sieunju/TIL/wiki/Controller-%EC%99%80-ViewHolder-%EA%B0%84%EC%9D%98-%EC%9D%98%EC%A1%B4%EC%84%B1-%EC%A0%9C%EA%B1%B0-%ED%95%B4%EB%B3%B4%EA%B8%B0)
    - [AAC Paging 라이브러리 안쓰고 페이징 구현해보기](https://github.com/sieunju/TIL/wiki/AAC-Paging-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EC%95%88%EC%93%B0%EA%B3%A0-MVVM-&-DataBinding-%ED%99%98%EA%B2%BD%EC%97%90%EC%84%9C-%ED%8E%98%EC%9D%B4%EC%A7%95-%EA%B5%AC%ED%98%84%ED%95%B4%EB%B3%B4%EA%B8%B0)
    - [유지보수하기 쉽게 DiffUtil 사용해보기](https://github.com/sieunju/TIL/wiki/%EC%9C%A0%EC%A7%80%EB%B3%B4%EC%88%98%ED%95%98%EA%B8%B0-%EC%89%BD%EA%B2%8C-DiffUtil-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0)
    - [유지보수하기 쉽게 DiffUtil 사용해보기-2](https://github.com/sieunju/TIL/wiki/%EC%9C%A0%EC%A7%80%EB%B3%B4%EC%88%98%ED%95%98%EA%B8%B0-%EC%89%BD%EA%B2%8C-DiffUtil-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EA%B8%B0-2)
    - [DiffUtil 고질적인 문제 처리해보기 v1](https://github.com/sieunju/TIL/wiki/DiffUtil-%EC%A2%80-%EB%8D%94-%EA%B0%9C%EC%84%A0%ED%95%B4%EB%B3%B4%EA%B8%B0-v1)

- CI
    - [Github Action으로 라이브러리 배포 돌려보기](https://github.com/sieunju/TIL/wiki/Github-Action%EC%9C%BC%EB%A1%9C-%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC-%EB%B0%B0%ED%8F%AC-%EB%8F%8C%EB%A0%A4%EB%B3%B4%EA%B8%B0)

- Lifecycle
    - [MVVM Lifecycle 의존하여 관리해보기, Activity 코드 제로에 도전해보기](https://github.com/sieunju/TIL/wiki/ViewModel-%EC%97%90%EC%84%9C-Lifecycle-%EC%9D%98%EC%A1%B4%ED%95%98%EC%97%AC-%EA%B4%80%EB%A6%AC%ED%95%B4%EB%B3%B4%EA%B8%B0,-Activity-%EC%BD%94%EB%93%9C-%EC%A0%9C%EB%A1%9C%EC%97%90-%EB%8F%84%EC%A0%84%ED%95%B4%EB%B3%B4%EA%B8%B0)
    - [MVVM 패턴에 맞게 BaseClass 살계해보기](https://github.com/sieunju/TIL/wiki/MVVM-Lifecycle-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-BaseClass-%EB%A7%8C%EB%93%A4%EC%96%B4%EB%B3%B4%EA%B8%B0)
    
- Architecture
    - [앱 모듈화 v1](https://github.com/sieunju/TIL/wiki/Multi-Feature-Refactoring)
    - [앱 모듈화 v2](https://github.com/sieunju/TIL/wiki/%EC%95%B1-%EB%AA%A8%EB%93%88%ED%99%94-v2)
- Migrate
    - [Rx에서 코루틴으로 점진적으로 전환 해보기](https://github.com/sieunju/TIL/wiki/Rx-%EC%97%90%EC%84%9C-Coroutine-%EC%9C%BC%EB%A1%9C-%EB%A7%88%EC%9D%B4%EA%B7%B8%EB%A0%88%EC%9D%B4%EC%85%98) 


### DI Graph
```Groovy
 ./gradlew projectDependencyGraph
```
![projectGraph](https://raw.githubusercontent.com/sieunju/TIL/develop/assets/projectGraph.png)

