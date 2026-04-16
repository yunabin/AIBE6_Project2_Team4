# 근육맨 키우기

Java CLI 기반의 텍스트 육성 시뮬레이션 게임입니다.
캐릭터를 생성하고 훈련과 식단 관리로 스탯을 키워 라이벌 3명을 모두 격파하면 챔피언이 됩니다.

---

## 게임 흐름

```
캐릭터 생성 (이름 + 체형 선택)
    ↓
매 턴: 훈련 선택 → 랜덤 이벤트 발생
    ↓
5턴마다: 라이벌 배틀 (3종목 대결)
    ↓
라이벌 3명 격파 → 챔피언 등극! 
```

---

## 실행 방법

```bash
# 1. 프로젝트 클론
git clone https://github.com/[username]/muscle-man-game.git

# 2. 빌드
./gradlew build

# 3. 실행
./gradlew run
```

---

## 게임 방법

### 1. 캐릭터 생성
이름을 입력하고 체형을 선택합니다.

| 체형 | 근력 | 지구력 | 체력 | 특징 |
|------|------|--------|------|------|
| 벌크형 | 80 | 40 | 60 | 근력 특화 |
| 밸런스형 | 60 | 60 | 60 | 균형 |
| 슬림형 | 50 | 70 | 50 | 지구력 특화 |

### 2. 훈련 선택 (매 턴)

| 훈련 | 효과 | 부상 중 가능? |
|------|------|--------------|
| ️ 웨이트 훈련 | 근력 +10, 체력 -5 | ❌ |
|  유산소 훈련 | 지구력 +10, 근력 +2 | ❌ |
|  식단 관리 | 체력 +15, 근력 +3 | ✅ |

### 3. 랜덤 이벤트

| 이벤트 | 확률 | 효과 |
|--------|------|------|
|  부상 | 15% | 다음 턴 훈련 불가, 체력 -20 |
|  치팅데이 | 20% | 체력 +30, 근력 -5 |
|  라이벌 도발 | 25% | 근력 +15, 지구력 +10 |

### 4. 라이벌 배틀 (5턴마다)
벤치프레스, 스쿼트, 데드리프트 3종목 대결 — **2승 이상이면 승리!**

| 종목 | 기준 스탯 |
|------|----------|
|  벤치프레스 | 근력 |
|  스쿼트 | 지구력 |
|  데드리프트 | 총 전투력 |

### 5. 라이벌 목록

| 순서 | 이름 | 난이도 |
|------|------|--------|
| 1번 | 헬스 초보 김민수 | ⭐ |
| 2번 | 3년차 안득근 | ⭐⭐ |
| 3번 | 근손실 공포증 박손실 | ⭐⭐⭐ |

---

## 프로젝트 구조

```
src/
├── main/java/org/project/
│   ├── Main.java                       # 진입점
│   ├── model/
│   │   ├── Stat.java                   # 스탯 (근력/지구력/체력)
│   │   ├── BodyType.java               # 체형 enum
│   │   ├── Character.java              # 플레이어 캐릭터
│   │   ├── InjuryStatus.java           # 부상 상태 관리
│   │   └── Rival.java                  # 라이벌
│   ├── training/
│   │   ├── Training.java               # 훈련 추상 클래스
│   │   ├── WeightTraining.java         # 웨이트 훈련
│   │   ├── CardioTraining.java         # 유산소 훈련
│   │   └── DietManagement.java         # 식단 관리
│   ├── event/
│   │   ├── RandomEvent.java            # 랜덤 이벤트 인터페이스
│   │   ├── InjuryEvent.java            # 부상 이벤트
│   │   ├── CheatDayEvent.java          # 치팅데이 이벤트
│   │   ├── RivalTauntEvent.java        # 라이벌 도발 이벤트
│   │   └── EventManager.java           # 이벤트 관리
│   ├── battle/
│   │   ├── BattleEvent.java            # 종목 승패 판정
│   │   └── BattleSystem.java           # 배틀 흐름 관리
│   ├── game/
│   │   ├── GameManager.java            # 게임 전체 흐름
│   │   └── RivalManager.java           # 라이벌 진행 관리
│   └── view/
│       └── ConsoleView.java            # 입출력 담당
└── test/java/org/project/
    ├── model/
    │   ├── StatTest.java
    │   └── CharacterTest.java
    ├── training/
    │   └── TrainingTest.java
    ├── event/
    │   └── EventManagerTest.java
    └── battle/
        └── BattleSystemTest.java
```

---

## 설계 구조

### 1. 템플릿 메서드 패턴 — `Training`
부상 체크 공통 로직을 추상 클래스가 담당합니다.
```java
public abstract class Training {
    public final void execute(Character character) {
        if (canNotExecute(character)) return; 
        doExecute(character);              
    }
    protected abstract void doExecute(Character character);
    protected boolean canNotExecute(Character character) {
        return character.isInjured(); // 기본: 부상 중이면 불가
    }
}
```

### 2. 단일 책임 원칙 — `BattleEvent`
종목별 승패 판정을 별도 클래스로 분리합니다.
```java

private final List<BattleEvent> events = List.of(
    new BattleEvent("벤치프레스", "🏋️ ", Stat::getStrength),
    new BattleEvent("스쿼트",    "🦵 ", Stat::getEndurance),
    new BattleEvent("데드리프트","💀 ", Stat::getTotalPower)
);
```

### 3. 역할 분리
| 클래스 | 책임 |
|--------|------|
| `GameManager` | 게임 전체 흐름 |
| `RivalManager` | 라이벌 진행 상태 |
| `InjuryStatus` | 부상 상태 관리 |
| `ConsoleView` | 입출력 전담 |

---

## 테스트 실행

```bash
./gradlew test
```

---

## 기술 스택

- Java 17
- Gradle
- JUnit 5
