# Reservation API Lambdaデプロイ手順

## 構成

Spring Boot
→ Docker
→ Amazon ECR
→ AWS Lambda
→ API Gateway
→ Neon PostgreSQL

## リージョン

ap-southeast-1

## Dockerイメージを確認

```bash
docker images
```

## ECRへログイン

```bash
aws ecr get-login-password \
  --region ap-southeast-1 \
| docker login \
  --username AWS \
  --password-stdin <AWS_ACCOUNT_ID>.dkr.ecr.ap-southeast-1.amazonaws.com
```

## ECR用タグを付与

```bash
docker tag \
  shop-reservation-api:lambda-local \
  <AWS_ACCOUNT_ID>.dkr.ecr.ap-southeast-1.amazonaws.com/shop-reservation-api:latest
```

## ECRへPush

```bash
docker push \
  <AWS_ACCOUNT_ID>.dkr.ecr.ap-southeast-1.amazonaws.com/shop-reservation-api:latest
```

## 動作確認

AWS Lambdaのテストイベントから
GET /api/reservations を実行。

結果

- HTTP 200
- Neon PostgreSQL接続成功
- reservationsテーブル12件取得成功
- Spring Boot正常起動

## Lambda環境変数

DB_URL=your_db_url

DB_USERNAME=your_db_username

DB_PASSWORD=your_db_password
