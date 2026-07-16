# shop-reservation-api

Java / Spring Boot を使った予約管理APIの学習用バックエンドリポジトリです。

PostgreSQLに保存されている予約データを取得し、フロントエンドから利用できるJSON APIとして返す
ことを目的としています。

## 技術スタック

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Flyway
- Docker / OrbStack

## 実装内容

- PostgreSQLコンテナの起動設定
- `reservations` テーブルの作成
- FlywayによるDBマイグレーション
- `Reservation` Entityの作成
- `ReservationRepository` の作成
- シードデータ投入
- 予約一覧取得APIの作成
- Service層の追加
- DTOによるレスポンス制御

## API

### 予約一覧取得

```http
 GET /api/reservations
```

PostgreSQLの reservations テーブルに登録されている予約一覧を取得します。

レスポンス例：

```json
[
  {
    "id": 1,
    "customerName": "田中太郎",
    "shopName": "渋谷店",
    "reservedAt": "2026-07-20T11:00:00",
    "status": "CONFIRMED"
  }
]
```

`customerEmail` はEntityには存在しますが、APIレスポンスには含めていません。
APIレスポンス用DTOとして `ReservationResponse` を使用しています。

## 主なディレクトリ構成

```text
  ├── gradle
  │   └── wrapper
  │       ├── gradle-wrapper.jar
  │       └── gradle-wrapper.properties
  ├── src
  │   ├── main
  │   │   ├── java/com/example/shopreservation
  │   │   │   ├── health
  │   │   │   │   └── HealthController.java
  │   │   │   ├── reservation
  │   │   │   │   ├── Reservation.java
  │   │   │   │   ├── ReservationController.java
  │   │   │   │   ├── ReservationRepository.java
  │   │   │   │   ├── ReservationResponse.java
  │   │   │   │   ├── ReservationSeeder.java
  │   │   │   │   ├── ReservationService.java
  │   │   │   │   └── ReservationStatus.java
  │   │   │   └── ShopReservationApiApplication.java
  │   │   └── resources
  │   │       ├── application.yaml
  │   │       └── db/migration
  │   │           └── V1__create_reservations_table.sql
  │   └── test
  ├── .gitattributes
  ├── .gitignore
  ├── build.gradle
  ├── docker-compose.yml
  ├── gradlew
  ├── gradlew.bat
  ├── HELP.md
  ├── README.md
  └── settings.gradle
```

## レイヤー構成

Controller
↓
Service
↓
Repository
↓
PostgreSQL

- `ReservationController`
  - HTTPリクエストを受け取る
  - `GET /api/reservations` を定義
- `ReservationService`
  - 予約一覧取得処理を担当
  - EntityをDTOに変換
- `ReservationRepository`
  - Spring Data JPAによるDBアクセス
- `Reservation`
  - `reservations` テーブルに対応するEntity
- `ReservationResponse`
  - APIレスポンス用DTO

  ## 起動方法

  DBコンテナを起動します。

  docker compose up -d

  Spring Bootアプリケーションを起動します。

  ./gradlew bootRun

## 動作確認

```bash
curl http://localhost:8080/api/reservations
```

またはブラウザで以下にアクセスします。

```text
http://localhost:8080/api/reservations
```

## 補足

現時点ではCORS設定は追加していません。

今後、別リポジトリで作成するVue.js / Next.jsフロントエンドからAPIを呼び出す際に、必要に応じてCORS設定を追加する予定です。
