package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@Slf4j
public class CurveController {

    private final CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        log.info("Request received to retrieve all CurvePoints");
        List<CurvePoint> curvePoints = curvePointService.findAll();
        model.addAttribute("curvePoints", curvePoints);

        log.info("Retrieved {} CurvePoints and added them to the model", curvePoints.size());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return Curve list
        log.info("Validating CurvePoint: {}", curvePoint);

        if (result.hasErrors()) {
            log.warn("Validation errors found for CurvePoint: {}", result.getAllErrors());
            return "curvePoint/add";
        }

        curvePointService.saveCurvePoint(curvePoint);
        List<CurvePoint> curvePoints = curvePointService.findAll();
        model.addAttribute("curvePoints", curvePoints);
        log.info("CurvePoint saved successfully: {}", curvePoint);
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        log.info("Request received to show update form for CurvePoint with id: {}", id);

        Optional<CurvePoint> curvePointOptional = curvePointService.findById(id);
        if (curvePointOptional.isPresent()) {
            model.addAttribute("curvePoint", curvePointOptional.get());
            log.info("Found CurvePoint with id: {} and added it to the model", id);
            return "curvePoint/update";
        } else {
            log.warn("CurvePoint with id: {} not found, redirecting to CurvePoint list", id);
            return "redirect:/curvePoint/list";
        }
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            // Return the user to the update form with error messages
            model.addAttribute("curvePoint", curvePoint);
            return "curvePoint/update";
        } else {
            // Call the service to update the CurvePoint and redirect to the Curve list
            curvePointService.updateCurvePoint(curvePoint);
            return "redirect:/curvePoint/list";
        }
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        log.info("Request received to delete CurvePoint with id: {}", id);
        try {
            curvePointService.deleteCurvePointById(id);
            log.info("Successfully deleted CurvePoint with id: {}", id);
        } catch (Exception e) {
            log.error("Error occurred while deleting CurvePoint with id: {}", id, e);
        }
        return "redirect:/curvePoint/list";
    }

}
