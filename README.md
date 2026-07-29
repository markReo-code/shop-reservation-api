# shop-reservation-api

Java / Spring Boot を使った予約管理APIの学習用バックエンドリポジトリです。

PostgreSQLに保存されている予約データを取得し、フロントエンドから利用できるJSON APIとして返すことを目的としています。

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
- Neon PostgreSQLへの接続

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

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
PostgreSQL
```

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

### ローカルでPostgreSQLを起動する場合

DBコンテナを起動します。

```bash
docker compose up -d
```

その後、以下の環境変数を指定してSpring Bootを起動します。

```bash
DB_URL='jdbc:postgresql://localhost:5432/shop_reservation' \
DB_USERNAME='shopuser' \
DB_PASSWORD='shop1234' \
SPRING_PROFILES_ACTIVE='local' \
./gradlew bootRun
```

### Neon PostgreSQLで起動する場合

Neon管理画面で **Connect → Java** を選択し、
表示された以下の接続情報を利用します。

空のデータベースで問題ありません。
初回起動時にFlywayが `reservations` テーブルを自動作成します。

接続情報は、接続文字列をそのまま利用するのではなく、
`DB_URL`・`DB_USERNAME`・`DB_PASSWORD` に分けて指定してください。

```bash
SPRING_PROFILES_ACTIVE='local' \
DB_URL='jdbc:postgresql://<NEON_HOST>/neondb?sslmode=require&channelBinding=require' \
DB_USERNAME='neondb_owner' \
DB_PASSWORD='<YOUR_PASSWORD>' \
./gradlew bootRun
```

現時点では学習用に、Neon接続時も `local` プロファイルを使用し、ローカル環境と同じシードデータを投入します。
実務寄りの構成へ発展させる段階で、`local` / `seed` / `prod` のようにプロファイルを分離する予定です。

> 実際の接続情報（DB_URL・DB_USERNAME・DB_PASSWORD）はGitへコミットしないでください。

## 動作確認

```bash
curl http://localhost:8080/api/reservations
```

またはブラウザで以下にアクセスします。

```text
http://localhost:8080/api/reservations
```

## AWS Lambdaへのデプロイ

Spring Boot APIをDockerイメージとしてビルドし、
Amazon ECR経由でAWS Lambdaへデプロイします。

詳細は以下を参照してください。

- [Lambdaデプロイ手順](docs/lambda-deployment.md)

## 補足

本リポジトリはAPI Gatewayを学習するためのバックエンドAPIの一つとして構築しています。

今後は複数のSpring Boot APIをAWS API Gateway経由で統合し、フロントエンドから単一エンドポイントで利用できる構成へ発展させる予定です。
