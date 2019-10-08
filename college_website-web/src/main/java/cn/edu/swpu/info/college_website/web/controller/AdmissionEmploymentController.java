package cn.edu.swpu.info.college_website.web.controller;

import cn.edu.swpu.info.college_website.web.common.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@Permission(resourceKey = "admissionEmployment")
@RequestMapping(value = "/admissionEmployment", method = {RequestMethod.GET, RequestMethod.POST})
public class AdmissionEmploymentController {

    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String main() {
        return "admissionEmployment/admissionEmployment";
    }

    @RequestMapping(value = "/generalize", method = {RequestMethod.GET})
    public String generalize() {
        return "admissionEmployment/generalize";
    }

    @RequestMapping(value = "/graduateEmployment", method = {RequestMethod.GET})
    public String graduateEmployment() {
        return "admissionEmployment/graduateEmployment";
    }

    @RequestMapping(value = "/innovation", method = {RequestMethod.GET})
    public String innovation() {
        return "admissionEmployment/innovation";
    }

    @RequestMapping(value = "/major", method = {RequestMethod.GET})
    public String major() {
        return "admissionEmployment/major";
    }

    @RequestMapping(value = "/postgraduateAdmission", method = {RequestMethod.GET})
    public String postgraduateAdmission() {
        return "admissionEmployment/postgraduateAdmission";
    }

    @RequestMapping(value = "/undergraduateAdmission", method = {RequestMethod.GET})
    public String undergraduateAdmission() {
        return "admissionEmployment/undergraduateAdmission";
    }

    @RequestMapping(value = "/mis", method = {RequestMethod.GET})
    public String mis(HttpServletRequest request, Model view) {
            return "admissionEmployment/mis";
    }
    @RequestMapping(value = "/dm1", method = {RequestMethod.GET})
    public String dm1(HttpServletRequest request, Model view) {
        return "admissionEmployment/dm1";
    }
    @RequestMapping(value = "/ece", method = {RequestMethod.GET})
    public String ece(HttpServletRequest request, Model view) {
        return "admissionEmployment/ece";
    }
    @RequestMapping(value = "/eea", method = {RequestMethod.GET})
    public String eea(HttpServletRequest request, Model view) {
        return "admissionEmployment/eea";
    }
    @RequestMapping(value = "/oeis", method = {RequestMethod.GET})
    public String oeis(HttpServletRequest request, Model view) {
        return "admissionEmployment/oeis";
    }
}