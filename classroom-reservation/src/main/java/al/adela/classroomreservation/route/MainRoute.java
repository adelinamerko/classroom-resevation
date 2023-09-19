package al.adela.classroomreservation.route;

import al.adela.classroomreservation.service.AdministratorService;
import al.adela.classroomreservation.service.ClassroomService;
import al.adela.classroomreservation.service.LecturerService;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;


public class  MainRoute extends RouteBuilder {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private ClassroomService classroomService;

    CamelContext context;

    public MainRoute (CamelContext context){
        super(context);
        this.context = context;
    }

    @Override
    public void configure() throws Exception {

        from("direct:loginAdministrator").id("loginAdministrator").autoStartup(true)
                .log("Started route : loginAdministrator")
                .bean(this.administratorService, "loginAdministrator(${headers.username}, ${headers.password})")
                .log("End route : loginAdministrator")
        .end();

        from("direct:loginLecturer").id("loginLecturer").autoStartup(true)
                .log("Started route : loginLecturer")
                .bean(this.lecturerService, "loginLecturer(${headers.username}, ${headers.password})")
                .log("End route : loginLecturer")
        .end();

        from("direct:getClassrooms").id("getClassrooms").autoStartup(true)
                .log("Started route : getClassrooms")
                .bean(this.classroomService, "getAllClassrooms()")
                .log("End route : getClassrooms")
        .end();

        from("direct:getLecturers").id("getLecturers").autoStartup(true)
                .log("Started route : getLecturers")
                .bean(this.lecturerService, "getAllLecturers()")
                .log("End route : getLecturers")
        .end();

        from("direct:addLecturer").id("addLecturer").autoStartup(true)
                .log("Started route : addLecturer")
                .bean(this.administratorService, "addLecturer(${headers.username}, ${headers.password})")
                .log("End route : addLecturer")
        .end();


        from("direct:addClassroom").id("addClassroom").autoStartup(true)
                .log("Started route : addClassroom")
                .bean(this.classroomService, "addClassroom(${headers.classroomNumber}, ${headers.seatsNumber})")
                .log("End route : addClassroom")
        .end();

        from("direct:removeClassroom").id("removeClassroom").autoStartup(true)
                .log("Started route : removeClassroom")
                .bean(this.classroomService, "removeClassroom(${headers.classroomId})")
                .log("End route : removeClassroom")
        .end();

        from("direct:updateClassroomWithId").id("updateClassroomWithId").autoStartup(true)
                .log("Started route : updateClassroomWithId")
                .bean(this.classroomService, "updateClassroom(${headers.classroomId}, ${headers.status})")
                .log("End route : updateClassroomWithId")
        .end();

        from("direct:getLecturerClassrooms").id("getLecturerClassrooms").autoStartup(true)
                .log("Started route : getLecturerClassrooms")
                .bean(this.lecturerService, "getClassrooms(${headers.lecturerId})")
                .log("End route : getLecturerClassrooms")
        .end();

        from("direct:reserveClassroom").id("reserveClassroom").autoStartup(true)
                .log("Started route : reserveClassroom")
                .bean(this.lecturerService, "reserveClassroom(${headers.classroomId}, ${headers.lecturerId})")
                .log("End route : reserveClassroom")
        .end();

        from("direct:freeClassroom").id("freeClassroom").autoStartup(true)
                .log("Started route : freeClassroom")
                .bean(this.lecturerService, "freeClassroom(${headers.classroomId}, ${headers.lecturerId})")
                .log("End route : freeClassroom")
        .end();

    }
}
