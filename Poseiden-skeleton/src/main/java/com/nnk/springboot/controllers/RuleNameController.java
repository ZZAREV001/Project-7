package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@Slf4j
public class RuleNameController {
    // TODO: Inject RuleName
    private final RuleNameService ruleNameService;

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        // TODO: find all RuleName, add to model
        log.info("Fetching all RuleNames");
        List<RuleName> ruleNames = ruleNameService.findAll();
        model.addAttribute("ruleNames", ruleNames);
        log.info("Found {} RuleNames", ruleNames.size());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return RuleName list
        if (result.hasErrors()) {
            log.error("Validation errors while submitting ruleName");
            return "ruleName/add";
        }

        ruleNameService.save(ruleName);
        log.info("RuleName saved successfully");
        model.addAttribute("ruleNames", ruleNameService.findAll());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get RuleName by Id and to model then show to the form
        Optional<RuleName> ruleNameOptional = ruleNameService.findById(id);

        if (ruleNameOptional.isPresent()) {
            model.addAttribute("ruleName", ruleNameOptional.get());
            return "ruleName/update";
        } else {
            log.error("RuleName with ID {} not found", id);
            return "redirect:/ruleName/list";
        }
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        if (result.hasErrors()) {
            // Return the user to the update form with error messages
            model.addAttribute("ruleName", ruleName);
            return "ruleName/update";
        } else {
            // Call the service to update the RuleName and redirect to the RuleName list
            ruleNameService.save(ruleName);
            log.info("RuleName updated successfully");
            return "redirect:/ruleName/list";
        }
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        ruleNameService.deleteById(id);
        log.info("RuleName with ID {} deleted successfully", id);
        return "redirect:/ruleName/list";

    }
}
