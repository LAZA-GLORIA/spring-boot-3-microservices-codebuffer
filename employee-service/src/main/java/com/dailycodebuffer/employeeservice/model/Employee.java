package com.dailycodebuffer.employeeservice.model;

/**
 *
 * @param id
 * @param departmentId
 * @param name
 * @param age
 * @param position
 *
 * Les record fonctionnalité ajoutée à Java 16. C'est une sorte de classe immuable principalement utilisée pour encapsuler un groupe de valeurs
 * Un moyen pratique pour écrire une classe de données sans écrire beaucoup de code
 *
 * Comme ils sont immuables, on peut modifier les champs une fois qu'ils sont définis. Si besoin de changer on doit créer une nouvelle instance
 * Fournissent des fonctionnalités par défaut comme les gettes, setters, equals, toString ;...
 * Plus la peine d'utiliser Lombok
 */
public record Employee(Long id, Long departmentId, String name, int age, String position) {
}
