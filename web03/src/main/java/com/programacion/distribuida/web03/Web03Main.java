package com.programacion.distribuida.web03;

import io.helidon.config.Config;
import io.helidon.dbclient.DbClient;
import io.helidon.http.media.jsonb.JsonbSupport;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;


public class Web03Main {
    private static DbClient dbClient;

    public static void handleBooks(ServerRequest req, ServerResponse res) {

        var books = dbClient.execute()
                .createQuery("SELECT * FROM books")
                .execute()
                .map(row -> Book.builder()
                        .title(row.column("title").getString())
                        .isbn(row.column("isbn").getString())
                        .build())
                .toList();

        books.forEach(System.out::println);

        res.send(books.toString());
    }

    public static void main(String[] args) {

        Config config = Config.create();

        var configHttp = config.get("server");
        var configDB = config.get("db");

        dbClient = DbClient.builder()
                .config(configDB)
                .build();

        WebServer.builder()
                .config(configHttp)
                .mediaContext(ctx->ctx.addMediaSupport(JsonbSupport.create()))
                .routing(it -> it
                        .get("/books", Web03Main::handleBooks)
                )
                .build()
                .start();
    }

}
