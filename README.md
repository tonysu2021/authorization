# Introduction

使用者認證中心。

## Members

Tony

## 專案SIT執行環境

* 主機資訊
  * gcp-swarm-manager
  * gcp-swarm-worker-1

* Docker 19.03.7、Docker compose 1.26.2
* 建立Docker Swarm ，並且創建 overlay 網路

  ```bash
  docker network create --driver overlay --attachable authnetwork
  ```

  指定哪幾台機器部屬對應服務

  ```bash
  # Remove label
   docker node update --label-rm auth gcp-swarm-manager || true
   docker node update --label-rm gateway gcp-swarm-manager || true
   # Add label
   docker node update --label-add auth=true gcp-swarm-manager || true
   docker node update --label-add gateway=true gcp-swarm-manager || true
  ```

## 專案Local執行環境

* Java 13 (jdk-13.0.2)
* apache-maven-3.6.3
* gradle-6.4
* host設定

  ```text
   # Auth
   172.20.111.184 auth-redis
   172.20.111.184 auth-postgres
   127.0.0.1 auth-server
   # Live
   127.0.0.1 live-streaming-video
  ```

## 專案架構說明

前端模組

* auth-admin-portal

  傳統One Model，採用thymeleaf，目前只是做為參考使用。
* auth-react-front

  作為後臺管理前端使用，目前已串接完成登入以及查所有用戶API。

OAuth2 Client 模組

* auth-common

  提供給其他系統，作為auth client (sdk)使用。

OAuth2 Resource 模組

* auth-gateway

  針對Http、Ws進行用戶驗證，若驗證成功，則會轉入對應系統(例如e-commerce)。

OAuth2 Server 模組

* auth-server

## 整合組件部屬到本機.m2

windows預設路徑 C:\Users\User\.m2\repository

```sh
# 必須先將共用組件commerce-cache部屬至.m2
cd ./auth-common \
   && mvn clean package deploy "-Dmaven.test.skip=true"
```
