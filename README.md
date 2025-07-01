# Alarm-Clock

## このアプリについて
Android Studioの勉強の際に作成した目覚ましアプリです。設定した時間でアラームを起動し、その日の天気・最高気温を音声で読み上げます。

## 開発環境
- Android Studio
- Gradle

## セットアップ方法
1. Android Studioでプロジェクトを開く
2. 必要な依存関係がダウンロードされるまで待つ
3. エミュレーターまたは実機でアプリを実行

## プロジェクト構造
```
├── app/                    # メインアプリケーション
│   ├── src/               # ソースコード
│   │   ├── main/          # メインコード
│   │   ├── test/          # ユニットテスト
│   │   └── androidTest/   # インストルメンテーションテスト
│   └── build.gradle       # アプリレベルのビルド設定
├── gradle/                # Gradleラッパー
├── .gitignore            # Git除外設定
├── build.gradle          # プロジェクトレベルのビルド設定
├── settings.gradle       # Gradleプロジェクト設定
└── README.md            # このファイル
```
