package al.adela.classroomreservation.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

public class RestConfig extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .bindingMode(RestBindingMode.off)
                .component("servlet")
                .contextPath("/")
                .scheme("http");


        rest().post("/api/login/administrator")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .param().name("username").type(RestParamType.query).description("Administrator username").dataType("string").endParam()
                .param().name("password").type(RestParamType.query).description("Administrator password").dataType("string").endParam()
                .to("direct:loginAdministrator");


        rest().post("/api/login/lecturer")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .param().name("username").type(RestParamType.query).description("Lecturer username").dataType("string").endParam()
                .param().name("password").type(RestParamType.query).description("Lecturer password").dataType("string").endParam()
                .to("direct:loginLecturer");


        rest().get("/api/view/classrooms")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .to("direct:getClassrooms");

        rest().get("/api/view/lecturers")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .to("direct:getLecturers");

        rest().post("/api/add/lecturer")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .param().name("username").type(RestParamType.query).description("Lecturer username to be added").dataType("integer").endParam()
                .param().name("password").type(RestParamType.query).description("Lecturer password to be added").dataType("integer").endParam()
                .to("direct:addLecturer");

        rest().post("/api/add/classroom")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .param().name("classroomNumber").type(RestParamType.query).description("Classroom number").dataType("integer").endParam()
                .param().name("seatsNumber").type(RestParamType.query).description("Classroom seats number").dataType("integer").endParam()
                .to("direct:addClassroom");

        rest().post("/api/remove/classroom/{classroomId}")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .to("direct:removeClassroom");

        rest().post("/api/update/classroom/{classroomId}")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .param().name("status").type(RestParamType.query).description("Classroom status to be updated").dataType("string").endParam()
                .to("direct:updateClassroomWithId");

        rest().get("/api/view/classrooms/{lecturerId}")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .to("direct:getLecturerClassrooms");

        rest().post("/api/reserve/classroom/{classroomId}/{lecturerId}")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .to("direct:reserveClassroom");

        rest().post("/api/free/classroom/{classroomId}/{lecturerId}")
                .bindingMode(RestBindingMode.json)
                .enableCORS(true)
                .produces("application/json")
                .to("direct:freeClassroom");

    }
}
