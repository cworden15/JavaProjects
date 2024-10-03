

import java.util.*;

// [constants]
// number of children fixed: 4
int NUMBER_OF_CHILDREN_FOR_EACH_NODE = 4;
int MAX_NUMBER_OF_LINE_SEGMENT_ON_EACH_NODE = 3;
String DEBUG_MSG_PRFX = "DEBUG_MSG: ";
void debug_print(String debug_msg) {
    println(DEBUG_MSG_PRFX + debug_msg);
}
//


class Rectangle {
    // note: only these 2 data are passed from outside during construction
    private Point bottomLeftPoint; // 0
    private Point topRightPoint; // 2
    

    // the below vars are later computed and assigned accordingly
    //
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;
    //
    private Point bottomRightPoint; // 1
    private Point topLeftPoint; // 3

    Rectangle(Point bottomLeftPoint, Point topRightPoint) {
        this.bottomLeftPoint = bottomLeftPoint;
        this.topRightPoint = topRightPoint;
        //
        this.minX = this.bottomLeftPoint.getX();
        this.maxX = this.topRightPoint.getX();
        this.minY = this.topRightPoint.getY();
        this.maxY = this.bottomLeftPoint.getY();
        //
        this.bottomRightPoint = new Point(this.maxX, this.maxY);
        this.topLeftPoint = new Point(this.minX, this.minY);
    }
    //
    public Point getBottomLeftPoint() {
        return bottomLeftPoint;
    }
    public Point getTopRightPoint() {
        return topRightPoint;
    }
    //
    boolean isPointInside(Point p) {
        
        boolean res = false;
        if(p.getX() >= this.minX && 
           p.getY() >= this.minY &&
           p.getX() <= this.maxX &&
           p.getY() <= this.maxY){
           
           res = true;  
        }
        return res;
    }
    //
    boolean isPointOnBorder(Point point) {
        boolean res = false;
        // check if the point is on any of the 4 lines
        // TODO: insert code
        return res;
    }
    boolean isLineIntersecting(HorizontalLineSegment segment) {     
        boolean res = false;
        if(segment.getLeftEndPoint().getX() < this.minX && 
          segment.getRightEndPoint().getX() > this.maxX && 
          segment.getRightEndPoint().getY() >= this.minY &&
          segment.getRightEndPoint().getY() <= this.maxY){
            res = true;
            
      }
          
        
        return res;
    }

    //
    boolean isLineFullyInside(Integer hlsId) {
        HorizontalLineSegment hls = inputHLSList.get(hlsId);

        boolean res = false;

        // test if BOTH of the 2 endpoints of the HORIZONTAL line is INSIDE
        if(
            this.isPointInside(hls.getLeftEndPoint())
            &&
            this.isPointInside(hls.getRightEndPoint())
        ) {
            res = true;
        }

        return res;
    }
    //
    boolean isLineStriclyOutside(Integer hlsId) {
        
        boolean res = false;
        // TODO: insert code

        return res;
    }

    //
    boolean isFullyInsideOfRect(Rectangle qRect) {
        boolean res = true;

        // TODO: insert code

        return res;
    }
    //
    boolean isFullyOutsideOfRect(Rectangle qRect) {
        boolean res = true;

        // TODO: insert code

        return res;
    }
    
    
    //
    public String toString() {
        return "Rectangle(bottomLeftPoint: " + this.bottomLeftPoint.toString() + ", topRightPoint:" + this.topRightPoint.toString() + ")";
    }
}

class HorizontalLineSegment {
    private int lineId = -1;
    private RgbColor rgbColor;
    private Point leftEndPoint;
    private Point rightEndPoint;
    
    HorizontalLineSegment() {
        this.lineId = -1;
        this.rgbColor = lineDefaultRgbColor; // default color
        this.leftEndPoint = null;
        this.rightEndPoint = null;
    }
    HorizontalLineSegment(int lineId, Point leftEndPoint, Point rightEndPoint) {
        this.lineId = lineId;
        this.rgbColor = lineDefaultRgbColor; // default color
        this.leftEndPoint = leftEndPoint;
        this.rightEndPoint = rightEndPoint;
    }
    //
    public int getLineId() {
        return this.lineId;
    }
    public void setLineId(int lineId) {
        this.lineId = lineId;
    }
    public Point getLeftEndPoint() {
        return this.leftEndPoint;
    }
    public Point getRightEndPoint() {
        return this.rightEndPoint;
    }
    public RgbColor getRgbColor() {
        return this.rgbColor;
    }
    //
    public void setRgbColor(RgbColor rgbColor) {
        this.rgbColor = rgbColor;
    }
    public void setLeftEndPoint(Point leftEndPoint) {
        this.leftEndPoint = leftEndPoint;
    }
    public void setRightEndPoint(Point rightEndPoint) {
        this.rightEndPoint = rightEndPoint;
    }
    //
    public String toString() {
        // return "HorizontalLineSegment(lp: " + this.leftEndPoint.toString() + ", rp:" + this.rightEndPoint.toString() + ")";
        return "s" + str(this.getLineId());
    }
}

class Point {
    private int x;
    private int y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    //
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
        
    //
    public String toString() {
        // return "Point(x: " + str(x) + ", y:" + str(y) + ")";
        return "(" + str(x) + ", " + str(y) + ")";
    }
}

class QuadTree{
  private Set<HorizontalLineSegment> sharedInfoWithNode = new HashSet<HorizontalLineSegment> (); 
  private TreeNode node;
  public ArrayList<Rectangle> sizeOfNode = new ArrayList<Rectangle>();
  public ArrayList<Integer> endPoints = new ArrayList<Integer>();
  
  private class TreeNode{
  public Rectangle size;
  private ArrayList<HorizontalLineSegment> segmentArray = new ArrayList<HorizontalLineSegment>();
  public int numOfEndPoints; 
  private Point leftPoint;
  private Point rightPoint;
  private TreeNode NW,NE,SW,SE;
  public HorizontalLineSegment nullSegment = new HorizontalLineSegment(5,new Point(0,0), new Point(0,0));
  
  

  TreeNode(Rectangle size){
    this.leftPoint = size.getBottomLeftPoint();
    this.rightPoint = size.getTopRightPoint();;
    this.numOfEndPoints = 0;
    this.size = size;
    this.nullSegment = null;
    for(int i = 0; i <= 3; i++){
      segmentArray.add(nullSegment);
    }
    
  }
  
  public Boolean isSubdivided(){
    if(this.NW == null){
      return false;
    }
    return true;
  }
   
  
  public Point getLeftPoint(){
   return this.size.getBottomLeftPoint();
  }  
  public Point getRightPoint(){   
    return this.size.getTopRightPoint();
  }  
  public Rectangle getSize(){
    return this.size;
  }  
  public ArrayList<HorizontalLineSegment> getSegmentArray(){
    return this.segmentArray;
  }
  
  
  
  public void clearSegmentArray(){
    this.segmentArray = new ArrayList<HorizontalLineSegment>();
  }  
}
  public void setSize(Point p1, Point p2){
    this.node = new TreeNode(new Rectangle(p1,p2));
  }    
  public String toString(){
    return "the height and width are " + this.node.getRightPoint().toString();
  }  
  public Rectangle getSize(){
    return node.getSize();
  }  
  public TreeNode getNode(){
    return node;
  }
  
  public void insertSegment(HorizontalLineSegment segment, TreeNode node){  
    this.sizeOfNode.add(node.getSize());
    if(node.getSegmentArray().contains(segment)){
      return;
    }
    
    int count = 4;
    for(HorizontalLineSegment s: node.getSegmentArray()){
      if(s == null){        
        count -= 1;
      }
    }
            
    if( count < 4 ){
     
     if(node.size.isPointInside(segment.getLeftEndPoint()) == true && node.size.isPointInside(segment.getRightEndPoint()) == true){
        node.numOfEndPoints += 2;       
        node.getSegmentArray().set(count, segment);
        count += 1;
              
        
        
      }else if(node.size.isPointInside(segment.getLeftEndPoint()) == true || node.size.isPointInside(segment.getRightEndPoint()) == true){
        node.numOfEndPoints += 1;
        node.getSegmentArray().set(count, segment);
        count += 1;
        
        
      }      
      else if(node.size.isLineIntersecting(segment)== true){        
        node.getSegmentArray().set(count, segment);        
        
        count += 1;
      }
                  
      if(count == 4){
        
        if(node.isSubdivided() == false){              
          divide(node); 
       }
      
        insertSegment(segment,node.NW);
        insertSegment(segment,node.NE);
        insertSegment(segment,node.SW);
        insertSegment(segment,node.SE); 
      }     
    }else{ 
        
       if(node.isSubdivided() == false){              
        divide(node); 
       }      
        insertSegment(segment,node.NW);
        insertSegment(segment,node.NE);
        insertSegment(segment,node.SW);
        insertSegment(segment,node.SE);                    
     }
  }
  
  public void insert(HorizontalLineSegment segment){
    insertSegment(segment,this.node);
  }
  
  public TreeNode createAChild(int x1 ,int y1, int x2, int y2, ArrayList<HorizontalLineSegment> segments){
    TreeNode tempNode = new TreeNode(new Rectangle(new Point(x1,y1), new Point(x2,y2)));
    
    tempNode.clearSegmentArray();
   
    for(HorizontalLineSegment s : segments){
      if(s != null){
      if(tempNode.size.isPointInside(s.getLeftEndPoint()) == true && tempNode.size.isPointInside(s.getRightEndPoint()) == true){
        node.numOfEndPoints += 2;       
        tempNode.segmentArray.add(s);        
               
        
        
      }else if(tempNode.size.isPointInside(s.getLeftEndPoint()) == true || tempNode.size.isPointInside(s.getRightEndPoint()) == true){
        node.numOfEndPoints += 1;       
        tempNode.segmentArray.add(s);        
               
        
        
      }else if(tempNode.size.isLineIntersecting(s) == true ){ 
        tempNode.segmentArray.add(s);        
              
        
        
        }     
      }
    }  
    
    if(tempNode.segmentArray.size() == 0){
      for(int i = tempNode.segmentArray.size() ; i < 4 ;i++){
        
        tempNode.segmentArray.add(null);
      }
    }
    else if(tempNode.segmentArray.size()  <= 3){
      for(int i = tempNode.segmentArray.size()  ; i < 4 ;i++){
        
        tempNode.segmentArray.add(null);
      }
    }
    
    return tempNode;
  }
  
  
  
  public ArrayList<Rectangle> getNumberOfNodes(){
    return this.sizeOfNode;
  }
  
  public void rangeReporting(Rectangle rect, TreeNode node){
    output.println("The quadtree was here: " node.size.toString());
    if(node == null) return;
    if(node.NW == null &&
       node.NE == null &&
       node.SW == null &&
       node.SE == null){
                   
         
      if(doRectsIntersect(rect, node) == true){
        for(HorizontalLineSegment s : node.segmentArray){
          if(s != null){
            if(rect.isPointInside(s.getLeftEndPoint()) || rect.isPointInside(s.getRightEndPoint())){
              this.sharedInfoWithNode.add(s);             
            }
          }        
         }  
        }       
     }
     else{
        if(doRectsIntersect(rect, node.NW) == true){
          rangeReporting(rect, node.NW);
        }
        if(doRectsIntersect(rect, node.NE) == true){
          rangeReporting(rect, node.NE);
        }
        if(doRectsIntersect(rect, node.SW) == true){
          rangeReporting(rect, node.SW);
        }
        if(doRectsIntersect(rect, node.SE) == true){
          rangeReporting(rect, node.SE);
        }
     }   
       
     
    
  }
  
  public void recurseRange(Rectangle rect){
    rangeReporting( rect, this.node);
    
  }
  
  public void divide(TreeNode node){
    node.NW = createAChild(node.getLeftPoint().getX(),
   (node.leftPoint.getY() + node.rightPoint.getY())/2,(node.leftPoint.getX() + node.rightPoint.getX())/2,
    node.rightPoint.getY(),node.segmentArray);
        
    node.NE = createAChild((node.leftPoint.getX() + node.rightPoint.getX())/2 + 1,
   (node.leftPoint.getY() + node.rightPoint.getY())/2,node.rightPoint.getX(),
    node.rightPoint.getY(),node.segmentArray); 
         
    node.SW = createAChild(node.getLeftPoint().getX(),
    node.leftPoint.getY(),(node.leftPoint.getX() + node.rightPoint.getX())/2,
   (node.leftPoint.getY() + node.rightPoint.getY())/2+1,node.segmentArray); 
        
    node.SE = createAChild((node.leftPoint.getX() + node.rightPoint.getX())/2+1,
    node.leftPoint.getY(),node.rightPoint.getX(),
    (node.leftPoint.getY() + node.rightPoint.getY())/2+1,node.segmentArray);
    
    
    
    
  }
    boolean doRectsIntersect(Rectangle rect, TreeNode node){
        boolean res = false;
        int rectLowerY = rect.getBottomLeftPoint().getY();
        int rectLowerX = rect.getBottomLeftPoint().getX();
        int rectUpperX = rect.getTopRightPoint().getX();
        int rectUpperY = rect.getTopRightPoint().getY(); 
        int nodeLowerY = node.size.getBottomLeftPoint().getY();
        int nodeLowerX = node.size.getBottomLeftPoint().getX();
        int nodeUpperY = node.size.getTopRightPoint().getY();
        int nodeUpperX = node.size.getTopRightPoint().getX();
        
        if((rectLowerX <= nodeLowerX && rectUpperX >= nodeLowerX) ||
        (rectLowerX <= nodeUpperX && rectUpperX >= nodeUpperX)){
            if((nodeLowerY >= rectLowerY && nodeUpperY <= rectLowerY) ||
            (nodeLowerY >= rectUpperY && nodeUpperY <= rectUpperY)){
              res = true;
            }
        }
        
        if((rectLowerY >= nodeLowerY && rectUpperY <= nodeLowerY) ||
        (rectLowerY >= nodeUpperY && rectUpperY <= nodeUpperY)){
            if((nodeLowerX <= rectLowerX && nodeUpperX >= rectLowerX) ||
            (nodeLowerX <= rectUpperX && nodeUpperX >= rectUpperX)){
              res = true;
            }       
         }
         if(rect.isPointInside(node.getLeftPoint()) == true && 
         rect.isPointInside(node.getRightPoint()) == true){
           res = true;
         }
         
    return res;
    
 }
  
  public void rangeCounting(Rectangle rect, TreeNode node){
    if(node == null) return  ;
    if(node.NW == null &&
       node.NE == null &&
       node.SW == null &&
       node.SE == null){
         
      if(rect.isPointInside(node.getLeftPoint()) == true && 
         rect.isPointInside(node.getRightPoint()) == true){
           
           for(HorizontalLineSegment s : node.segmentArray){
            if(s != null){               
               print("hi  ");
               endPoints.add(1);     
            
          }
         }
      }
                   
      else if(doRectsIntersect(rect, node) == true){
        for(HorizontalLineSegment s : node.segmentArray){
          if(s != null){             
            
             if(rect.isPointInside(s.getLeftEndPoint()) && 
                rect.isPointInside(s.getRightEndPoint())){
                  
                    endPoints.add(1);
                    print("hisds  ");
              
                 
                }
             else if(rect.isPointInside(s.getRightEndPoint())
             && node.size.isPointInside(s.getRightEndPoint())){
                endPoints.add(1);
                print("hisdsbsdf ");
             }else if(rect.isPointInside(s.getLeftEndPoint()) && 
             node.size.isPointInside(s.getLeftEndPoint())){
                endPoints.add(1);
                print("hi");
             }
             else if(rect.isPointInside(s.getRightEndPoint())
             && node.size.isPointInside(s.getLeftEndPoint())){
                endPoints.add(1);
                print("hisdsbsdf ");
             }else if(rect.isPointInside(s.getLeftEndPoint()) && 
             node.size.isPointInside(s.getRightEndPoint())){
                endPoints.add(1);
             }
          }        
         }
        }       
     }
     else{
        if(doRectsIntersect(rect, node.NW) == true){
          rangeCounting(rect, node.NW);
        }
        if(doRectsIntersect(rect, node.NE) == true){
          rangeCounting(rect, node.NE);
        }
        if(doRectsIntersect(rect, node.SW) == true){
          rangeCounting(rect, node.SW);
        }
        if(doRectsIntersect(rect, node.SE) == true){
          rangeCounting(rect, node.SE);
        }
     } 
  }
  
  public int callCounting(Rectangle rect){
    rangeCounting(rect,this.node);
    
    return endPoints.size();
  }
  
  
  
    
    
}
