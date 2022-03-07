public class Section {
    Row[] rows;
    String name;
    int rowCount;

    Section(String name, int[] rowSeatCount) {
        this.name = name;
        this.rowCount = rowSeatCount.length;
        rows = new Row[this.rowCount];
        for (int i=0; i<rowCount; i++) {
            int seatCount = rowSeatCount[i];
            rows[i] = new Row(i, seatCount);
        }
    }
    static class Row {
        int rowNdx;
        Seat[] seats;
        Row(int rowNdx, int seatCount) {
            this.rowNdx = rowNdx;
            seats = new Seat[seatCount];
            for (int seatNdx=0; seatNdx<seatCount; seatNdx++) {
                seats[seatNdx] = new Seat(rowNdx, seatNdx);
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tName: ").append(name).append(Debug.NL).append("\t\tRows: ").append(rowCount).append(Debug.NL);
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            sb.append("\t\tRow number: ").append(i).append(", seats: ").append(row.seats.length);
            return String.valueOf(sb);
        }
        return String.valueOf(sb);
    }

}