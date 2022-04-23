package fr.uvsq.hal.pglp.rpgdao;

import fr.uvsq.hal.pglp.rpg.Character;

import java.util.Optional;

/**
 * La classe <code>DAO</code> est la classe de base pour les DAO de l'application.
 *
 * @author SARAH&MELISSA
 * @version 2022
 */
public interface Dao<T> {
    boolean create(T objet);

    boolean create(Character objet);

    Optional<T> read(String identifier);

    boolean update(T objet);

    void delete(T objet);

    boolean update(Character objet);

    void delete(Character objet);
}