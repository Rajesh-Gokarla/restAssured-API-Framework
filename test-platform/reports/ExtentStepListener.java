package reports;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

public class ExtentStepListener implements ConcurrentEventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {

        publisher.registerHandlerFor(
                TestStepFinished.class,
                this::handleStepFinished
        );
    }

    private void handleStepFinished(TestStepFinished event) {

        // Ignore hooks
        if (!(event.getTestStep() instanceof PickleStepTestStep)) {
            return;
        }

        PickleStepTestStep step =
                (PickleStepTestStep) event.getTestStep();

        String stepText =
                step.getStep().getKeyword()
                        + step.getStep().getText();

        var scenario = ExtentTestManager.getScenario();
        if (scenario == null) return;

        Status status = event.getResult().getStatus();

        switch (status) {
            case PASSED ->
                    scenario.pass(stepText);
            case FAILED ->
                    scenario.fail(stepText)
                            .fail(event.getResult().getError());
            case SKIPPED ->
                    scenario.skip(stepText);
            default -> {
            }
        }
    }
}