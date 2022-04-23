package fr.uvsq.hal.pglp.rpgdao;

import fr.uvsq.hal.pglp.rpg.Character;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * La classe <code>EmployeeSerializedDAO</code> permet de charger/sauvegarder un personnel.
 *
 * @author SARAH&MELISSA
 * @version 2022
 */
public class CharacterSerializedDao implements Dao<Character> {
    private final Path directory;

    public CharacterSerializedDao(Path directory) {
        this.directory = directory;
    }

    @Override
    public boolean create(Character character) {
        String filename = character.getNom();
        Path employeePath = directory.resolve(filename);
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(employeePath))) {
            oos.writeObject(character);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public Optional<Character> read(String identifier) {
        Path characterPath = directory.resolve(identifier);
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(characterPath))) {
            Character character = (Character) ois.readObject();
            return Optional.of(character);
        } catch (IOException | ClassNotFoundException e) {
            // return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Character character) {
        delete(character);
        return create(character);
    }

    @Override
    public void delete(Character character) {
        String filename = character.getNom();
        Path characterPath = directory.resolve(filename);
        try {
            Files.deleteIfExists(characterPath);
        } catch (IOException e) {
            // Ignore the error
        }
    }
}