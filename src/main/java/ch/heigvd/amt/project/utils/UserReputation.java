package ch.heigvd.amt.project.utils;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@EqualsAndHashCode
public class UserReputation {
    private Long id;
    private String username;

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class Badge {
        private int id;
        private String name;
        private String color;
        private String description;
    }

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class Pointscale {
        private String label;
        private int pointCounter;
    }

    @Singular
    private List<Badge> badges = new ArrayList<Badge>();
    @Singular
    private List<Pointscale> pointscales = new ArrayList<Pointscale>();
}
