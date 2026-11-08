package ejercicios;

import estructuras.Cola;

public class Ejercicio5 {

    public static void ejecutar() {
        System.out.println("=========== EJERCICIO 5: Árboles ===========");
        int[] lista = {45, 33, 29, 28, 22, 32, 34, 39, 36, 43, 60, 47, 46, 50, 49, 55, 70, 69, 65, 86, 79, 88};

        System.out.println("a) Árbol ABB (FE = altura derecha - altura izquierda):");
        ArbolBB abb = new ArbolBB();
        for (int dato : lista) {
            abb.insertar(dato);
        }
        abb.imprimir();

        System.out.println("\nb) Recorridos del ABB:");
        Cola<Integer> preorden = abb.preorden();
        Cola<Integer> postorden = abb.postorden();
        System.out.println("   Preorden : " + preorden);
        System.out.println("   Inorden  : " + abb.inorden());
        System.out.println("   Postorden: " + postorden);
        System.out.println("   Amplitud : " + abb.amplitud());

        System.out.println("\nc) Árbol AVL construido con el preorden, paso a paso:");
        ArbolAVL avl = new ArbolAVL();
        while (!preorden.estaVacia()) {
            avl.insertar(preorden.desencolar());
        }
        System.out.println("\n   AVL final - Preorden: " + avl.preorden());

        System.out.println("\nd) Eliminar el nodo 45 del AVL:");
        avl.eliminar(45);

        // e) Rojo-Negro con el recorrido en postorden
        System.out.println("\ne) Árbol Rojo-Negro construido con el postorden, paso a paso:");
        ArbolRojoNegro rn = new ArbolRojoNegro();
        while (!postorden.estaVacia()) {
            rn.insertar(postorden.desencolar());
        }
    }
}

