package ch.heigvd.amt.project.domain.answer;

import ch.heigvd.amt.project.domain.Id;

import java.util.UUID;

public class AnswerId extends Id {

    public AnswerId() { super(); }

    public AnswerId(String id) { super(id); }

    public AnswerId(UUID id) { super(id); }
}
