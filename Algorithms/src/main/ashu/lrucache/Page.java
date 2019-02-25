package main.ashu.lrucache;

public class Page {
   private int pageNumber;
   private String pageName;
   private String pageContent;
public Page(int pageNumber, String pageName, String pageContent) {
	super();
	this.pageNumber = pageNumber;
	this.pageName = pageName;
	this.pageContent = pageContent;
}
public int getPageNumber() {
	return pageNumber;
}
public void setPageNumber(int pageNumber) {
	this.pageNumber = pageNumber;
}
public String getPageName() {
	return pageName;
}
public void setPageName(String pageName) {
	this.pageName = pageName;
}
public String getPageContent() {
	return pageContent;
}
public void setPageContent(String pageContent) {
	this.pageContent = pageContent;
}
@Override
public String toString() {
	return"PageNo = "+this.pageNumber+" Content = "+this.pageContent;
}
   
}
