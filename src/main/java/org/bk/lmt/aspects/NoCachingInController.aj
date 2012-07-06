//package org.bk.lmt.aspects;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Controller;
//import javax.annotation.Resource;
//public aspect NoCachingInController {
//    declare error: execution(@RequestMapping @Cacheable * (@Controller *).*(..)) : "Contoller methods should not have the Cacheable annotation";
//    declare warning: get(@Resource *  *.*):"Resource discouraged..";
//}