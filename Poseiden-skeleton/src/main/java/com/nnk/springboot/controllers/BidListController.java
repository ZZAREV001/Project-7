package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

    private final BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        log.info("Request received to retrieve all bids");

        List<BidList> bidLists = bidListService.findAll();
        model.addAttribute("bidLists", bidLists);

        log.info("Successfully retrieved {} bids", bidLists.size());

        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list
        if (result.hasErrors()) {
            log.warn("Bid validation failed: {}", result.getAllErrors());
            model.addAttribute("bid", bid);
            return "bidList/add";
        }

        bidListService.save(bid);
        log.info("Bid successfully saved: {}", bid);
        model.addAttribute("bids", bidListService.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        // TODO: get Bid by Id and to model then show to the form
        Optional<BidList> bidOptional = bidListService.findById(id);
        if (bidOptional.isPresent()) {
            model.addAttribute("bid", bidOptional.get());
            return "bidList/update";
        } else {
            log.error("Bid with id {} not found", id);
            return "redirect:/bidList/list";
        }
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Bid and return list Bid
        if (result.hasErrors()) {
            // Return the user to the update form with error messages
            model.addAttribute("bid", bidList);
            return "bidList/update";
        } else {
            // Call the service to update the BidList and redirect to the Bid list
            bidListService.save(bidList);
            log.info("Bid with id {} updated successfully", id);
            return "redirect:/bidList/list";
        }
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteById(id);
        log.info("Bid with id {} deleted successfully", id);
        return "redirect:/bidList/list";
    }
}
