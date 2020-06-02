import java.util.ArrayList;
import java.util.Scanner;

class Edge {
    Place place;
    int distance;
    public Edge(Place p, int d) {
        this.place = p;
        this.distance = d;
    }
}

class Place {
    String name;
    ArrayList<Edge> toVisit = new ArrayList<>();
    public Place(String n) {
        this.name = n;
    }
}

public class ShortestPath {
    public ArrayList<Place> place = new ArrayList<>();

    public void insertPlace(String name) {
        place.add(new Place(name));
    }

    public void connector(String at, String to, int dis) {
        Place atPlace = searchPlace(at);
        Place toPlace = searchPlace(to);

        int x = place.indexOf(atPlace);
        place.get(x).toVisit.add(new Edge(toPlace, dis));
        x = place.indexOf(toPlace);
        place.get(x).toVisit.add(new Edge(atPlace, dis));
    }

    public Place searchPlace(String name) {
        for(int i=0;i<place.size();i++)
            if(place.get(i).name.compareTo(name)==0)
                return place.get(i);
        return null;
    }

    public int shortestWay(ArrayList<Place> x, String at, String to) {
        Place from = searchPlace(at);
        if(at.compareTo(to)==0)
            return 0;
        else x.add(from);
        int min = Integer.MAX_VALUE;
        if(from.toVisit.size() == 0)
            return 0;
        ArrayList<Place> way = new ArrayList<>();
        for(int i = 0; i < from.toVisit.size(); i++) {
            ArrayList<Place> y = (ArrayList<Place>) x.clone();
            if(x.contains(from.toVisit.get(i).place)) continue;
            int tmp = from.toVisit.get(i).distance + shortestWay(y, from.toVisit.get(i).place.name, to);
            if(min > tmp && tmp >= 0){
                way.clear();
                min = tmp;
                for(int k = 0; k < y.size(); k++)
                    way.add(y.get(k));
            }
        }
        x.clear();
        for(int k=0; k < way.size(); k++)
            x.add(way.get(k));
        return min;
    }    

    public static void main(String[] args) {
        Scanner key = new Scanner(System.in);
        ShortestPath path = new ShortestPath();
        path.insert();
        path.print(path.place);
        ArrayList<Place> x = new ArrayList<>();
        System.out.print("from: ");
        String from = key.next();
        System.out.print("to: ");
        String to = key.next();
        int y = path.shortestWay(x, from, to);

        for(int i = 0; i < x.size(); i++)
           System.out.print(x.get(i).name+" -> ");
        System.out.println(to+"\nminimum distance: "+y);
    }

    public void print(ArrayList<Place> place) {
        for(int i = 0; i < place.size(); i++){
            System.out.println("from: "+place.get(i).name);
            for(int j = 0; j < place.get(i).toVisit.size(); j++) {
                System.out.print("to: "+place.get(i).toVisit.get(j).place.name+" ");
                System.out.println("-> "+place.get(i).toVisit.get(j).distance+" ");
            } 
            System.out.println("");
        }
    }

    public void insert() {
        String[] input = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", 
                            "11", "12", "13", "14", "15", "16", "17", "18"};
        for(int i = 0; i < input.length; i++)
            insertPlace(input[i]);

        connector("1", "2", 125);
        connector("2", "3", 646);
        connector("3", "4", 106);
        connector("4", "5", 512);
        connector("5", "6", 104);
        connector("5", "7", 106);
        connector("6", "9", 682);
        connector("7", "8", 100);
        connector("9", "10", 762);
        connector("10", "11", 1260);
        connector("11", "12", 711);
        connector("8", "13", 656);
        connector("13", "14", 606);
        connector("14", "9", 945);
        connector("14", "15", 600);
        connector("15", "16", 470);
        connector("16", "11", 514);
        connector("16", "17", 700);
        connector("17", "18", 280);
        connector("18", "12", 623);
    }
}