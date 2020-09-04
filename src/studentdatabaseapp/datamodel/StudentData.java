package studentdatabaseapp.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class StudentData {
    private static final String STUDENTS_FILE = "students.xml";

    private static final String STUDENT = "student";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String SCHOOL_YEAR = "school_year";
    private static final String COURSE = "course";

    private ObservableList<Student> students;

    public StudentData() {
        students = FXCollections.observableArrayList();
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student item) {
        students.add(item);
    }

    public void deleteStudent(Student item) {
        students.remove(item);
    }

    public void loadStudents() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(STUDENTS_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Student student = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a student item, we create a new student
                    if (startElement.getName().getLocalPart().equals(STUDENT)) {
                        student = new Student();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(FIRST_NAME)) {
                            event = eventReader.nextEvent();
                            student.setFirstName(event.asCharacters().getData());
                            continue;
                        }
                    }
                    if (event.asStartElement().getName().getLocalPart()
                            .equals(LAST_NAME)) {
                        event = eventReader.nextEvent();
                        student.setLastName(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SCHOOL_YEAR)) {
                        event = eventReader.nextEvent();
                        student.setSchoolYear(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(COURSE)) {
                        event = eventReader.nextEvent();
                        student.setCourse(event.asCharacters().getData());
                        continue;
                    }
                }

                // If we reach the end of a student element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(STUDENT)) {
                        students.add(student);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveStudent() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(STUDENTS_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement studentsStartElement = eventFactory.createStartElement("",
                    "", "students");
            eventWriter.add(studentsStartElement);
            eventWriter.add(end);

            for (Student student: students) {
                saveStudent(eventWriter, eventFactory, student);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "contacts"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Student file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void saveStudent(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Student student)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create student open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", STUDENT);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, FIRST_NAME, student.getFirstName());
        createNode(eventWriter, LAST_NAME, student.getLastName());
        createNode(eventWriter, SCHOOL_YEAR, student.getSchoolYear());
        createNode(eventWriter, COURSE, student.getCourse());

        eventWriter.add(eventFactory.createEndElement("", "", STUDENT));
        eventWriter.add(end);
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}