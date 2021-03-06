package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TaskRepository taskRepository;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage Your tasks");
        functionality.add("Provide connection with trello account");
        functionality.add("Application allows sends tasks to trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:3000/fe");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Goodbye " + adminConfig.getAdminName() +  "!");
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        context.setVariable("show_button",false);
        context.setVariable("is_friend",true);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildCountingTasksEmail(String message) {
        List<Task> taskList = taskRepository.findAll();

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:3000/fe");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Goodbye " + adminConfig.getAdminName() +  "!");
        context.setVariable("company_details", adminConfig.getCompanyDetails());
        context.setVariable("task_list", taskList);
        context.setVariable("one_task", taskList.size() == 1);

        return templateEngine.process("mail/counting-tasks-mail", context);
    }
}
