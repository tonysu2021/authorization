# Auth Server

## MapStruct 套件

在Run專案前，須執行編譯產生mapStruct的code

```sh
mvn clean package "-Dmaven.test.skip=true"
```

## 測試

* 網頁測試

  目前規劃採用 Selenium IDE (研究中)

* 點對點 API測試

  VsCode 請安裝 REST Client套件，執行 xx.http 檔案，例如request.http。

* 單元/整合測試
  
  詳見 src/test/java/com/auth ，整合測試有另外的 application-test.yml 使用 H2 DB測試。
