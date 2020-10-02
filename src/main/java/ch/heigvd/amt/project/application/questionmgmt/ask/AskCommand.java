package ch.heigvd.amt.project.application.questionmgmt.ask;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AskCommand {
    private String label;
    private String content;
}
