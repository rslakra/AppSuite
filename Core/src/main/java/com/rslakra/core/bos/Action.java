package com.rslakra.core.bos;

/**
 * An action performed by an agent and participants upon an object. The execution of the action may produce a result.
 * <p>
 * More specific types CreateAction DeleteAction SearchAction UpdateAction
 *
 * @author Rohtash Lakra (rlakra)
 * @created 1/27/22 10:21 AM
 */
public class Action {

    // Indicates the current state of an action taken, e.g. active, in progress, completed, failed, etc.
    private ActionStatus actionStatus;

    // Connect an action to its direct performer or owner. E.g., Amit in "Amit wants to create a reservation at a restaurant together with Steve".
// Person or Organization
    private Organization agent;

    // The object upon which the action is carried out, whose state is kept intact or changed. E.g., a ticket or reservation.
// Thing
    private Thing object;

    // Participants are other co-agents that participate in an action indirectly. E.g. Steve in "Amit wants to create a reservation at a restaurant together with Steve".
// Person or Organization
    private Organization participant;

    // The result produced in an action. E.g., new action, receipt, etc.
// Thing
    private Thing result;

}
