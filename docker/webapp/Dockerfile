# ---- ビルド: 当時の Eclipse 環境（Tomcat 9 / Java 17）の代わりにコマンドでコンパイルする ----
FROM tomcat:9.0-jdk17 AS build
WORKDIR /build
COPY src/main/webapp ./war
COPY src/main/java ./java
# 当時は仮想マシン上の MySQL(localhost) に接続していた。コンテナでは DB のホスト名が db-mysql になるため、
# DBManager.java 内にコメントで用意されていた「コンテナで動作する場合」の接続先に、ビルド時だけ置き換える。
RUN sed -i 's#jdbc:mysql://localhost:3306/db_dev#jdbc:mysql://db-mysql:3306/db_dev#' java/design/DBManager.java \
 && mkdir -p war/WEB-INF/classes \
 && javac -encoding UTF-8 -cp /usr/local/tomcat/lib/servlet-api.jar -d war/WEB-INF/classes java/design/*.java \
 && cd war && jar cf /build/webapp-group09.war .

# ---- 実行 ----
FROM tomcat:9.0-jdk17
ENV TZ=Asia/Tokyo
ADD https://repo1.maven.org/maven2/com/mysql/mysql-connector-j/8.4.0/mysql-connector-j-8.4.0.jar /usr/local/tomcat/lib/
COPY --from=build /build/webapp-group09.war /usr/local/tomcat/webapps/
EXPOSE 8080
