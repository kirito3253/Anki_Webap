# 高専生クイズ（暗記用Webアプリケーション）

高専の授業の課題として、4名のチームで開発した4択クイズのWebアプリケーションです。
暗記学習を「他人と競うゲーム」にすること（ゲーミフィケーション）で、学習意欲を高めることを目的としています。

- 開発期間：2024年6月〜10月
- 構成：Java Servlet / JSP（MVC）＋ MySQL、Tomcat 9 上で動作
- 連携：Raspberry Pi（結果画面の「友達に知らせる」ボタンで、ユーザ名と得点を音声で読み上げ）

## 主な機能

| 画面 | 内容 |
|---|---|
| トップページ | クイズ開始・スコア一覧へのリンク |
| ログインページ | ユーザ名を入力し、登録済みユーザか確認 |
| クイズページ | DBからランダムに問題を出題（4択） |
| 正解／不正解ページ | 正誤判定の結果と正答を表示し、次の問題へ |
| リザルトページ | 得点に応じたメッセージとドーナツグラフ（CSSのみで描画） |
| スコア一覧ページ | 全ユーザのスコアを一覧表示 |

## 担当部分

- **クイズ出題機能**：`MondaiServlet.java`（doGet）、`questionBean.java`、`viewquestion.jsp`、`resultpage.jsp`
  - セッションで「何問目か」と得点を管理し、規定の問題数に達したらスコアをDBに登録して結果画面へ遷移
- **正誤判定機能**：`MondaiServlet.java`（doPost）、`TFCheckLogic.java`、`correct.jsp`、`wrong.jsp`
  - 最終問題のときだけ「次の問題へ」ボタンを「結果を見る」に切り替え
- **クイズ関連ページのデザイン**：`qdesign.css`（背景・選択肢ボタン・得点のドーナツグラフ）
- **問題作成**：4択クイズ約50問（`jikken.sql`）
- その他：Raspberry Pi連携のための結果画面の修正、ログイン処理の実装補助、JavaDocの作成（`doc/`）

トップページ、ログイン処理、スコア一覧などは他のメンバーが担当しました。

## ファイル構成

```
src/main/java/design/     Servlet・JavaBean・DB接続クラス、テーブル定義と問題データ(jikken.sql)
src/main/webapp/          JSP・HTML・CSS・画像
doc/                      JavaDoc
docker-compose.yml, docker/   再実行用の Docker 設定（下記）
```

## 実行方法

[Docker Desktop](https://www.docker.com/products/docker-desktop/) をインストールした状態で、このフォルダで次を実行します。

```
docker compose up --build
```

起動後、ブラウザで http://localhost:8080/webapp-group09/ を開きます。
ログインでは登録済みのユーザ名（例：`user1`）を入力してください。終了は `Ctrl + C` → `docker compose down` です。

※「友達に知らせる」ボタンは、当時の学内ネットワークにあったRaspberry Piへ送信する仕様のため、この環境では動作しません。

## このリポジトリについて

授業当時は、仮想マシン（Ubuntu）上の Eclipse・Tomcat・MySQL で開発・実行していました。
その実行環境を削除したため、残っていた仮想ディスクから当時のGitリポジトリを取り出し、再実行用の Docker 設定（`docker-compose.yml`、`docker/`）を追加しています。
公開にあたり、ソースコード・JavaDoc・テストデータからメンバーの氏名を削除し、登録ユーザ名を `user1`〜`user4` に置き換えました。それ以外の処理は当時のままです。
DBの接続先はビルド時に、`DBManager.java` 内にコメントで用意していた「コンテナで動作する場合」の設定へ置き換えています。
