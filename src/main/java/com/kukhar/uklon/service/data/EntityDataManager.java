package com.kukhar.uklon.service.data;

import com.kukhar.uklon.model.CsvEntity;
import com.kukhar.uklon.model.Location;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public abstract class EntityDataManager<E extends CsvEntity> {

    @Autowired
    protected Faker faker;

    public abstract List<E> generateRandomEntities();

    public abstract void readEntitiesFromLines(List<String> lines);

    public abstract String getEntityHeaders();

    public Location createRandomLocation() {
        return new Location(
                Double.parseDouble(faker.address().latitude()),
                Double.parseDouble(faker.address().longitude()),
                faker.number().randomDouble(2, 0, 500),
                faker.number().randomDouble(2, 0, 10)
        );
    }
}
