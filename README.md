### Spring Boot サンプルコードです
サンプルとして以下を実装しました。

- ログイン機能
- メンバー登録機能
- メンバー一覧機能

### 実行したいときは？
ログインのときに使用するDB領域が必要です。
src/main/resources/application.properties のDB接続先を環境に合わせて書き換えてください。
Application起動時に、以下のテーブルが自動的に作成されます。
- memberテーブル
- activity_logテーブル


### 構成

This template is compatible with [Auto DevOps](https://docs.gitlab.com/ee/topics/autodevops/).

If Auto DevOps is not already enabled for this project, you can [turn it on](https://docs.gitlab.com/ee/topics/autodevops/#enabling-auto-devops) in the project settings.