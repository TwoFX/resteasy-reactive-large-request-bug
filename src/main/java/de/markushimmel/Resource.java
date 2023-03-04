package de.markushimmel;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("resource")
public class Resource {

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<ResponseObject> create(RequestObject request) {
        MyEntity entity = new MyEntity();
        entity.setData(request.getData());

        return Panache.<MyEntity>withTransaction(entity::persist)
                .map(ent -> new ResponseObject(ent.id));
    }
}
