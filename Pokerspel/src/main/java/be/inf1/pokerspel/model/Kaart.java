/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package be.inf1.pokerspel.model;

/**
 *
 * @author hannes Depoorter, Jasper Gebruers
 */


public class Kaart {
    public enum Rang {
        Twee(2), Drie(3), Vier(4), Vijf(5), Zes(6), Zeven(7),
        Acht(8), Negen(9), Tien(10), Boer(11),
        Queen(12), King(13), Aas(14);

        private final int waarde;

        Rang(int waarde) {
            this.waarde = waarde;
        }

        public int getWaarde() {
            return waarde;
        }
    }

    public enum Figuur {
        Harten, Ruiten, Klaveren, Schoppen
    } 
    
    private final Rang rang;
    private final Figuur figuur;
    private boolean omgedraaid;
    
    public Kaart(Rang rang,Figuur figuur){
        this.rang = rang;
        this.figuur = figuur;
        omgedraaid = false;
    }

    public void draaiOm() {
       omgedraaid = !omgedraaid;    
    }
    
    public Rang getRang(){
        return rang;
    }
    
    public Figuur getFiguur(){
        return figuur;
    }
        
    @Override
    public String toString() {
        return figuur + "-" + rang;
    }
    
    public boolean isOmgedraaid() {
        return omgedraaid;
    }
    
    
}
