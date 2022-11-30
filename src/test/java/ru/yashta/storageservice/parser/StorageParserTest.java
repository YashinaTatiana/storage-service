package ru.yashta.storageservice.parser;

import jakarta.xml.bind.JAXBException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.yashta.storageservice.BaseTest;
import ru.yashta.storageservice.model.BoxDto;
import ru.yashta.storageservice.model.LinkType;
import ru.yashta.storageservice.model.Storage;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@Transactional
public class StorageParserTest extends BaseTest {

    @Autowired
    private StorageParserFactory factory;

    @ParameterizedTest
    @MethodSource("parserProvider")
    void testParseXml(LinkType type, String path) throws JAXBException, IOException {
        Storage storage = factory.getStorageParserByPath(type).parseXmlFile(path);
        Assertions.assertThat(storage).isNotNull();
        Assertions.assertThat(storage.getBoxes()).hasSize(1);
        Assertions.assertThat(storage.getItems()).hasSize(2);
    }

    @ParameterizedTest
    @MethodSource("parserProvider")
    void testParseXml_NestedElements(LinkType type, String path) throws JAXBException, IOException {
        Storage storage = factory.getStorageParserByPath(type).parseXmlFile(path);
        Assertions.assertThat(storage).isNotNull();
        Assertions.assertThat(storage.getBoxes()).hasSize(1);
        BoxDto parent = storage.getBoxes().get(0);

        Assertions.assertThat(parent.getBoxes()).hasSize(2);
        Assertions.assertThat(parent.getItems()).hasSize(3);
        Assertions.assertThat(parent.getBoxes().get(0).getItems()).hasSize(2);
        Assertions.assertThat(parent.getBoxes().get(1).getItems()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource("parserProvider")
    void testParseXml_Attributes(LinkType type, String path) throws JAXBException, IOException {
        Storage storage = factory.getStorageParserByPath(type).parseXmlFile(path);
        Assertions.assertThat(storage).isNotNull();

        Assertions.assertThat(storage.getBoxes()).hasSize(1);
        Assertions.assertThat(storage.getBoxes().get(0).getId()).isEqualTo(1);

        Assertions.assertThat(storage.getItems()).hasSize(2);
        Assertions.assertThat(storage.getItems().get(0).getId()).isEqualTo(6);
        Assertions.assertThat(storage.getItems().get(0).getColor())
                .isNotNull()
                .isEqualTo("green");
    }

    static Stream<Arguments> parserProvider() {
        return Stream.of(
                arguments(LinkType.CLASSPATH, "test.xml"),
                arguments(LinkType.FILE, "src/test/resources/test.xml")
        );
    }
}
