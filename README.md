# Introduction

使用者認證中心。

## Members

Tony

## 專案Local執行環境

* Java 13 (jdk-13.0.2)
* apache-maven-3.6.3
* gradle-6.4

## 專案架構說明

前端模組

* auth-admin-portal : 傳統One Model，採用thymeleaf。
* auth-react-front

OAuth2 Client 模組

* auth-common

OAuth2 Resource 模組

* auth-gateway

OAuth2 Server 模組

* auth-server

## 整合組件部屬到本機.m2

預設路徑 C:\Users\User\.m2\repository

```sh
# 必須先將共用組件commerce-cache部屬至.m2
cd ./auth-common \
   && mvn clean package deploy "-Dmaven.test.skip=true"
```
