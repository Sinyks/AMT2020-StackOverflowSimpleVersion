package ch.heigvd.amt.project.domain.post;

import ch.heigvd.amt.project.domain.Id;

import java.util.UUID;

public class PostId extends Id {

    public PostId() { super(); }

    public PostId(String id) { super(id); }

    public PostId(UUID id) { super(id); }
}
