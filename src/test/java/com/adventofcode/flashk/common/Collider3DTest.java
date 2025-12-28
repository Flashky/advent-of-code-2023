package com.adventofcode.flashk.common;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.IO.println;

import module java.base;
import org.junit.jupiter.api.Test;

class Collider3DTest {

    @Test
    void testIdenticalObjectsCollide() {
        // Un cubo de 1x1x1 en la misma posición (2,2,2)
        Vector3 pos = new Vector3(2, 2, 2);
        Collider3D a = new Collider3D(pos, pos);
        Collider3D b = new Collider3D(pos, pos);

        assertTrue(a.collidesWith(b), "Objetos idénticos deben colisionar");
    }

    @Test
    void testBrickHorizontalOverlap() {
        // Brick horizontal (0,0,10 hasta 1,0,10) - Volumen 2
        Collider3D horizontal = new Collider3D(new Vector3(0, 0, 10), new Vector3(1, 0, 10));

        // Un cubo simple que ocupa la celda (1,0,10)
        Vector3 cubePos = new Vector3(1, 0, 10);
        Collider3D cube = new Collider3D(cubePos, cubePos);

        assertTrue(horizontal.collidesWith(cube), "El cubo debe colisionar con el extremo del brick horizontal");
    }

    @Test
    void testBrickVerticalCollision() {
        // Brick vertical (0,0,1 hasta 0,0,10) - Volumen 10
        Collider3D vertical = new Collider3D(new Vector3(0, 0, 1), new Vector3(0, 0, 10));

        // Un objeto en el centro del brick vertical (z=5)
        Vector3 midPos = new Vector3(0, 0, 5);
        Collider3D midCube = new Collider3D(midPos, midPos);

        assertTrue(vertical.collidesWith(midCube), "El objeto debe colisionar con el centro del brick vertical");
    }

    @Test
    void testAlmostTouchingButNoCollision() {
        // Cubo A en el origen
        Collider3D a = new Collider3D(new Vector3(0, 0, 0), new Vector3(0, 0, 0));
        // Cubo B en la siguiente celda X=1
        Collider3D b = new Collider3D(new Vector3(1, 0, 0), new Vector3(1, 0, 0));

        assertFalse(a.collidesWith(b), "Objetos en celdas contiguas no deben colisionar");
    }

    @Test
    void testMissOnOneAxisOnly() {
        // Comparten X e Y, pero Z es diferente
        Collider3D base = new Collider3D(new Vector3(5, 5, 5), new Vector3(5, 5, 5));
        Collider3D above = new Collider3D(new Vector3(5, 5, 6), new Vector3(5, 5, 6));

        assertFalse(base.collidesWith(above), "No deben colisionar si hay un hueco en el eje Z");
    }

    @Test
    void testTransformUpdatesBounds() {
        // Objeto en (0,0,0)
        Vector3 start = new Vector3(0, 0, 0);
        Vector3 end = new Vector3(0, 0, 0);
        Collider3D collider = new Collider3D(start, end);

        // Mover 5 unidades en X
        collider.transform(new Vector3(5, 0, 0));

        // Verificar que los límites se actualizaron
        assertEquals(5, collider.getMinX());
        assertEquals(5, collider.getMaxX());
    }

    @Test
    void testTransformWithSameInstance() {
        Vector3 sharedPoint = new Vector3(1, 1, 1);
        // start y end son LA MISMA instancia
        Collider3D collider = new Collider3D(sharedPoint, sharedPoint);

        // Mover 1 unidad
        collider.transform(new Vector3(1, 0, 0));

        // Si tu lógica del 'if(start != end)' funciona:
        // El valor final debe ser 2 (1 + 1)
        // Si fallara y lo moviera dos veces, sería 3 (1 + 1 + 1)
        assertEquals(2, collider.getMinX(), "Debería haberse movido solo una vez");
    }

    @Test
    void testNegativeCoordinates() {
        // Un brick que va desde un punto positivo a uno negativo
        // start(1,1,1) a end(-1,-1,-1)
        Collider3D brick = new Collider3D(new Vector3(1,1,1), new Vector3(-1,-1,-1));

        // Un cubo en el origen (0,0,0)
        Collider3D center = new Collider3D(new Vector3(0,0,0), new Vector3(0,0,0));

        assertTrue(brick.collidesWith(center), "Debe detectar colisión aunque los puntos start/end estén invertidos");
        assertEquals(-1, brick.getMinX());
        assertEquals(1, brick.getMaxX());
    }
}