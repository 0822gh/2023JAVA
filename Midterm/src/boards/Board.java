package boards;

import java.sql.Date;
import com.mysql.cj.jdbc.Blob;

public class Board {
	private int bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private Date bdate;
	private String bfilename;
	private Blob bfiledata;
	
    // 각 필드에 대한 setter 메서드
    public void setBno(int bno) {this.bno = bno;}

    public void setBtitle(String btitle) {this.btitle = btitle;}

    public void setBcontent(String bcontent) {this.bcontent = bcontent;}

    public void setBwriter(String bwriter) {this.bwriter = bwriter;}

    public void setBdate(Date bdate) {this.bdate = bdate;}

    public void setBfilename(String bfilename) {this.bfilename = bfilename;}

    public void setBfiledata(java.sql.Blob blob) {this.bfiledata = (Blob) blob;}

    // 각 필드에 대한 getter 메서드
    public int getBno() {return bno;}

    public String getBtitle() {return btitle;}

    public String getBcontent() {return bcontent;}

    public String getBwriter() {return bwriter;}

    public Date getBdate() {return bdate;}

    public String getBfilename() {return bfilename;}

    public Blob getBfiledata() {return bfiledata;}
}