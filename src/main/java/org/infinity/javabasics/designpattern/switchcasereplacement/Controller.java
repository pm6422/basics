//package org.infinity.javabasics.designpattern.switchcasereplacement;
//
//public class Controller {
//    @Autowired
//    private InitCaseBeanMapComponent mapComponent;
//
//    @RequestMapping(value = "/switch/case", method = RequestMethod.GET)
//    public BaseResponse caseInfo(@RequestParam String type, @RequestParam String name) {
//        BaseResponse response = new BaseResponse(StatusCode.Success);
//        try {
//            CaseRequest request = new CaseRequest(type, name);
//            CaseEnum caseEnum = CaseEnum.valueOf(request.getType());
//            CaseInterface caseInterface = mapComponent.getProcessMap().get(caseEnum);
//            response.setData(caseInterface.execute(request));
//        } catch (Exception e) {
//            response = new BaseResponse(StatusCode.Fail.getCode(), e.getMessage());
//        }
//        return response;
//    }
//}
