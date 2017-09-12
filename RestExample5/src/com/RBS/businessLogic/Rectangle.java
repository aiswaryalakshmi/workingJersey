package com.RBS.businessLogic;


public class Rectangle {
	private double length;
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getBreadth() {
		return breadth;
	}
	public void setBreadth(double breadth) {
		this.breadth = breadth;
	}

	private double breadth;
	
	
	
	public Rectangle(){
		
	}
	public Rectangle(double length, double breadth){
		this.length = length;
		this.breadth = breadth;
	}
	
	
	
	
	public double calculateArea(){
		
		double area = length * breadth;
		
		return area;
	}
	
	public double calculatePerimeter(){
		double perimeter = 2.0 * (length + breadth);
		
		return perimeter;
	}
	
    

	

}
