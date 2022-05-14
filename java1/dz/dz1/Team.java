package java1.dz.dz1;

public class Team implements Runable, Rideable, Swimable{
    private String teamName = "Team has no name";
    private Human[] members;
    private boolean[] membersState;


    public Team(String teamName, Human member1, Human member2, Human member3, Human member4) { //выглядит страшно
        Human[] members = new Human[4];
        members[0] = member1;
        members[1] = member2;
        members[2] = member3;
        members[3] = member4;
        this.members = members;
        this.membersState = new boolean[members.length];
        for (int i = 0; i < membersState.length; i++) {
            membersState[i] = true;
        }
        if (teamName.length() > 0) this.teamName = teamName;
    }

    public Team(String teamName, Human... members) {
        if (members == null || members.length < 1) {
            throw new NullPointerException();
        }
        if (teamName.length() > 0) this.teamName = teamName;
        this.members = members;
        membersState = new boolean[members.length];
        for (int i = 0; i < membersState.length; i++) {
            membersState[i] = true;
        }
    }

    public void showResults() {
        System.out.println("\nTeam " + teamName + " results:");
        for (int i = 0; i < members.length; i++) {
            System.out.print(members[i].getName() + ": ");
            System.out.println((membersState[i]) ? "passed" : "failed");
        }
    }

    public void printTeamMembers() {
        System.out.println("\nTeam " + teamName + " results:");
        for (int i = 0; i < members.length; i++) {
            System.out.print(members[i].getName());
        }
    }

    @Override
    public boolean ride(int distance) {
        for (int i = 0; i < members.length; i++) {
            if (! members[i].ride(distance)) {
                membersState[i] = false;
            }
        }
        return false;
    }

    @Override
    public boolean run(int distance) {
        for (int i = 0; i < members.length; i++) {
            if (! members[i].run(distance)) {
                membersState[i] = false;
            }
        }
        return false;
    }

    @Override
    public boolean swim(int distance) {
        for (int i = 0; i < members.length; i++) {
            if (! members[i].swim(distance)) {
                membersState[i] = false;
            }
        }
        return false;
    }
}
