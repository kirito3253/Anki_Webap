#!/bin/bash
# 当時のSQL(jikken.sql)をそのまま流し込む。
# 先頭の DROP TABLE は初回起動時にテーブルが無くエラーになるため --force で無視する。
mysql --force --default-character-set=utf8mb4 -uroot -p"$MYSQL_ROOT_PASSWORD" "$MYSQL_DATABASE" < /sql/jikken.sql 2>&1 | grep -v "Unknown table" || true
