import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class Tetris extends JFrame{
	public static void main(String[] args) {
		new Tetris();
	}
	int row=30,collumn=20;
	int score=5; 
	int rows=35;
	int squaresize=2;
	double speed=0.5;
	int[] newshape=new int[0];
	int[][][] shape=new int[][][] {
        { { 1, 1 },   { 2, 1 },   { 2, 2 },   { 2, 2 } },
        { { 1, 2 },   { 2, 1 },   { 3, 1 },   { 3, 1 } },
        { { 1, 1 },   { 2, 1 },   { 3, 1 },   { 3, 1 } },
        { { 1, 1 },   { 2, 2 },   { 2, 2 },   { 2, 2 } },
        { { 1, 1 },   { 1, 2 },   { 1, 2 },   { 1, 2 } },
        { { 1, 1 },   { 2, 2 },   { 3, 1 },   { 3, 1 } },
        { { 2, 1 },   { 2, 1 },   { 2, 1 },   { 2, 1 } },
        { { 1, 1 },   { 2, 2 },   { 3, 3 },   { 2, 2 } }
    };
    Color[] color=new Color[]{Color.GRAY,Color.GREEN,Color.PINK,Color.ORANGE,Color.LIGHT_GRAY,Color.YELLOW,Color.BLUE,Color.CYAN};
	Tetris(){ 
		super("Tetris");
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter()
		{public void windowClosing(WindowEvent e){System.exit(0);}});
		JPanel p=new JPanel();
		add(p);
		JPanel p2=new JPanel();
		JLabel scoreLabel=new JLabel("scoring factor: 5");
		JLabel rowsLabel=new JLabel("number of rows required for each Level of difficulty: 35");
		JLabel speedLabel=new JLabel("speed factor: 0.5");
		JSlider scoreSlider=new JSlider(1,10);
		scoreSlider.setMajorTickSpacing(9);
		scoreSlider.setMinorTickSpacing(1);
		scoreSlider.setPaintTicks(true);
		scoreSlider.setPaintLabels(true);
		scoreSlider.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	 score=((JSlider)e.getSource()).getValue();
	            scoreLabel.setText("scoring factor: "+score);
	         }
	      });
		JSlider rowsSlider=new JSlider(20,50);
		rowsSlider.setMajorTickSpacing(5);
		rowsSlider.setMinorTickSpacing(1);
		rowsSlider.setPaintTicks(true);
		rowsSlider.setPaintLabels(true);
		rowsSlider.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	 rows=((JSlider)e.getSource()).getValue();
	            rowsLabel.setText("number of rows required for each Level of difficulty: "+rows);
	         }
	      });
		JSlider speedSlider=new JSlider(1,10);
		speedSlider.setMajorTickSpacing(9);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	 speed=(double)((JSlider)e.getSource()).getValue()/10;
	            speedLabel.setText("speed factor: "+speed);
	         }
	      });
		JLabel rowLabel=new JLabel("number of rows: "+row);
		JLabel collumnLabel=new JLabel("number of collumns: "+collumn);
		JSlider rowSlider=new JSlider(20,40);
		rowSlider.setMajorTickSpacing(4);
		rowSlider.setMinorTickSpacing(2);
		rowSlider.setPaintTicks(true);
		rowSlider.setPaintLabels(true);
		rowSlider.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	 row=((JSlider)e.getSource()).getValue()/2*2;
	            rowLabel.setText("number of rows: "+row);
	         }
	      });
		JSlider collumnSlider=new JSlider(10,30);
		collumnSlider.setMajorTickSpacing(5);
		collumnSlider.setMinorTickSpacing(1);
		collumnSlider.setPaintTicks(true);
		collumnSlider.setPaintLabels(true);
		collumnSlider.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	 collumn=((JSlider)e.getSource()).getValue();
	        	 collumnLabel.setText("number of collumns: "+collumn);
	         }
	      });
		JLabel square=new JLabel("square size: "+2);
		JSlider squareSize=new JSlider(1,3);
		squareSize.setMajorTickSpacing(1);
		squareSize.setMinorTickSpacing(1);
		squareSize.setPaintTicks(true);
		squareSize.setPaintLabels(true);
		squareSize.addChangeListener(new ChangeListener() {
	         public void stateChanged(ChangeEvent e) {
	        	 squaresize=((JSlider)e.getSource()).getValue();
	            square.setText("square size: "+squaresize);
	         }
	      });
		JButton begin=new JButton("Start Game");
		begin.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  remove(p);
			  int a=Math.round((float)(collumn*52+80)/3*squaresize);
			  int b=Math.round((float)(row*26+80)/3*squaresize);
			  setSize(a,b);
			  Board board=new Board(score,rows,(float)speed,row,collumn,newshape);
			  add("Center",board );
			  board.start();
		  }
		});
		
		JButton select=new JButton("Select New Shapes");
		select.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  JFrame frame = new JFrame();
			  JPanel p2=new JPanel();
			  frame.setTitle("select new shapes");
			  frame.setLocationRelativeTo(null);
			  frame.setVisible(true);
			  JPanel p=new newBoard();
			  p2.setLayout(new FlowLayout(20,30,0));
			  frame.add(p);
			  frame.setSize(800, 200);
			  JCheckBox[] box=new JCheckBox[]{new JCheckBox("select"),new JCheckBox("select"),new JCheckBox("select"),new JCheckBox("select"),new JCheckBox("select"),new JCheckBox("select"),new JCheckBox("select"),new JCheckBox("select")};
			  for(int i=0;i<8;i++)
				  p2.add(box[i]);
			  JButton finish=new JButton("finish");
				finish.addActionListener(new ActionListener()
				{
				  public void actionPerformed(ActionEvent e)
				  {
					  ArrayList<Integer> list=new ArrayList<Integer>();
					  for(int i=0;i<8;i++){
						  if(box[i].isSelected()) list.add(i);
					  }
					  newshape=new int[list.size()];
					  for(int i=0;i<list.size();i++)
						  newshape[i]=list.get(i)+8;
					  frame.setVisible(false);
				  }
				});
				
				p.add(finish,BorderLayout.NORTH);
				p.add(p2);
		  }
		});
		p.add(scoreLabel);
		p.add(scoreSlider);
		p.add(rowsLabel);
		p.add(rowsSlider);
		p.add(speedLabel);
		p.add(speedSlider);
		p.add(rowLabel);
		p.add(rowSlider);
		p.add(collumnLabel);
		p.add(collumnSlider);
		p.add(square);
		p.add(squareSize);
		p2.add(select);
		p2.add(begin);
		p.add(p2);
		p.setLayout(new GridLayout(13,1));		
		p2.setLayout(new GridLayout(1,2));
		
		pack();
		p.setVisible(true);
		p2.setVisible(true);
		show();
	}
	class newBoard extends JPanel{
		public newBoard(){
			
		}
		public void board(Graphics g,int x,int y,Color z,int curx){
			x=20*x;
			y=60-20*y;
			g.setColor(z);
			g.fillRect(x+curx, y+80, 20, 20);
			g.setColor(Color.black);
			g.drawRect(x+curx, y+80, 20, 20);
		}
		public void paintComponent(Graphics g){
			for(int k=0;k<8;k++){
				for (int i = 0; i < 4 ; i++) {
		            int x=shape[k][i][0];
		            int y=shape[k][i][1];
		            board(g,x,y,color[k],100*k);
		        }
			}
		}
	}
}
class Board extends Canvas implements ActionListener{
	int score=0,level=1,lines=0; 
	int row,collumn;
	float S;
	float FS=1;
	int M,N;
	Shape curShape;
	Shape nextShape;
	int[] newshape;
	int[][] curBoard;
	Color[][] boardColor;
	boolean rightClock=false;
	boolean leftClock=false;
	boolean top=false;
	int curX,curY;
	boolean in=false;
	boolean inShape;
	int centerX, centerY;
	float pixelSize, rWidth, rHeight, xP, yP;
	Timer timer;
	public boolean downcompleted=false;
	
	Board(int M,int N,float S,int row,int collumn,int[] newshape){ 
		this.M=M;this.N=N;this.S=S;this.row=row;this.collumn=collumn;this.newshape=newshape;
		rWidth = collumn*52+80; rHeight = row*26+80;
		curBoard=new int[row][collumn];
		boardColor=new Color[row][collumn];
		addMouseMotionListener(new MouseAdapter()
		{ public void mouseMoved(MouseEvent evt){
			boolean a=in;
			xP = fx(evt.getX()); yP = fy(evt.getY());
			if(xP>-26*collumn&&xP<0&&yP>-13*row&&yP<13*row){
				in=true;
				timer.stop();
				boolean b=inShape;
				if(tryReplace()==true&&b==false){
					Shape shape2=new Shape(newshape);
					shape2.setRandomShape();
					while(shape2.getColor()==Color.white||shape2.getShape()==curShape.getShape()||nextShape.getShape()==shape2.getShape()){
						shape2.setRandomShape();
					}
					curShape=shape2;
					score=score-level*M;
					repaint();
				}
 			}
			else{
				in=false;
				timer.start();
			}
			if(a!=in) repaint();
			}
		});
		addMouseListener(new MouseAdapter()
		{ public void mousePressed(MouseEvent evt){
			xP = fx(evt.getX()); yP = fy(evt.getY());
			if(xP>50&&xP<150&&yP>-260&&yP<-220){
				System.exit(1);
 			}
		}});
		addMouseListener(new MouseAdapter()
		{ public void mouseClicked(MouseEvent evt){
			if(!in){
				if(SwingUtilities.isLeftMouseButton(evt)){
					if(tryMove(-1)) curX--;
				}
				else if(SwingUtilities.isRightMouseButton(evt)){
					if(tryMove(1)) curX++;
				}
				repaint();
			}
		}});
		addMouseWheelListener(new MouseAdapter()
		{ public void mouseWheelMoved(MouseWheelEvent evt){
			if(!in){
				if(evt.getWheelRotation()>0){
					if(checkRotation(1))
						curShape=curShape.rotateRight();
				}
				if(evt.getWheelRotation()<0){
					if(checkRotation(-1))
						curShape=curShape.rotateLeft();
				}
				repaint();
			}
		}});
		curX=(collumn+1)/2-2;
		curY=row;
		timer = new Timer((int) (500/FS), this);
	    timer.start(); 
	}
	public void start(){;
		curShape=new Shape(newshape);
		nextShape=new Shape(newshape);
		curShape.setRandomShape();
		nextShape.setRandomShape();
		timer.start();		
	}
	public boolean tryReplace(){
		int x=(int)(xP+26*collumn)/26+1;
		int y=(int)(yP+13*row)/26+1;
		boolean a=false;
		for (int i = 0; i < 4; ++i) {
			int nowX=curX+curShape.x(i);
			int nowY=curY+curShape.y(i);
			if(nowX==x&&nowY==y) a=true;
			int nextX=curX+nextShape.x(i);
			int nextY=curY+nextShape.y(i);
            if(nextX<1||nextX>collumn||nextY<1||nextY>row||curBoard[nextY-1][nextX-1]==1){
            	return false;
            }
            inShape=a;
		}
		return(inShape);
	}
	void initgr(){  
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
		centerX = maxX/2; centerY = maxY/2;
	}
	int iX(float x){return Math.round(centerX + x/pixelSize);}
	int iY(float y){return Math.round(centerY - y/pixelSize);}
	float fx(int x){return (x - centerX) * pixelSize;}
	float fy(int y){return (centerY - y) * pixelSize;}
	public void board1(Graphics g,int x,int y,Color z){
		if(y>row) return;
		x=iX(-26*collumn+26*x-26);
		y=iY(-13*row+26*y);
		g.setColor(z);
		g.fillRect(x, y, Math.round(26/pixelSize), Math.round(26/pixelSize));
		g.setColor(Color.black);
		g.drawRect(x, y, Math.round(26/pixelSize), Math.round(26/pixelSize));
	}
	public void board2(Graphics g,int x,int y,Color z){
		x=iX(135-26*3+26*x);
		y=iY(200-26+26*y);
		g.setColor(z);
		g.fillRect(x, y, Math.round(26/pixelSize), Math.round(26/pixelSize));
		g.setColor(Color.black);
		g.drawRect(x, y, Math.round(26/pixelSize), Math.round(26/pixelSize));
	}
	private boolean tryMove(int k)
	{
		for (int i = 0; i < 4; ++i) {
	        int x = curX + curShape.x(i);
	        int y = curY + curShape.y(i);
	        if(y>row) return false;
	        if(k>0){
	        	if (x==collumn||curBoard[y-1][x]==1) return false;
	        }
	        else if(k<0)
	        	if(x==1||curBoard[y-1][x-2]==1) return false;
	    }
	    return true;
	}
	public void paint(Graphics g){
		initgr();
		g.drawRect(iX(-26*collumn),iY(13*row),Math.round(26*collumn/pixelSize),Math.round(26*row/pixelSize));
		g.drawRect(iX(50),iY(275),Math.round(170/pixelSize),Math.round(125/pixelSize));
		g.drawRect(iX(50),iY(-220),Math.round(100/pixelSize),Math.round(40/pixelSize));
		g.setFont(new Font("Helvetica",Font.PLAIN,(int)(20/pixelSize)));
		g.drawString("Level:", iX(50), iY(50));
		g.drawString("Lines:", iX(50), iY(0));
		g.drawString("Score:", iX(50), iY(-50));
		g.drawString("QUIT", iX(75), iY(-250));
		g.drawString(""+level,iX(200),iY(50));
		g.drawString(""+lines,iX(200),iY(0));
		g.drawString(""+score,iX(200),iY(-50));
		for(int x=0;x<collumn;x++){
			for(int y=0;y<row;y++){
				if(curBoard[y][x]==1)
					board1(g,x+1,y+1,boardColor[y][x]);
			}
		}
		for (int i = 0; i < 4; ++i) {
            int x = nextShape.x(i);
            int y = nextShape.y(i);
            board2(g,x,y,nextShape.getColor());
		}
		for (int i = 0; i < 4; ++i) {
            int x = curX + curShape.x(i);
            int y = curY + curShape.y(i);
            board1(g,x,y,curShape.getColor());
		}
		if(in){
			g.setColor(Color.blue);
			g.drawRect(iX(-13*collumn-60),iY(25),Math.round(120/pixelSize),Math.round(50/pixelSize));
			g.setFont(new Font("SansSerif", Font.BOLD, (int)(25/pixelSize)));
			g.drawString("PAUSE", iX(-13*collumn-40), iY(-12));
		}
	}
	public void addCurBoard(){
		for (int i = 0; i < 4; ++i) {
            int x = curX + curShape.x(i);
            int y = curY + curShape.y(i);
            if(y>row) {
            	top=true;
            }
            else{
            	curBoard[y-1][x-1]=1;
                boardColor[y-1][x-1]=curShape.getColor();
            }
            
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		checkDropped();
		if(downcompleted){
			addCurBoard();
			if(top==true){
				timer.removeActionListener(this);
			}
			doLineDown();
			downcompleted=false;
			curShape=nextShape;
			nextShape=new Shape(newshape);
			nextShape.setRandomShape();
			curX=(collumn+1)/2-2;
			curY=row;
		}
		else{
			curY--;
		}
		repaint();
	}
	public void doLineDown(){
		boolean[] removeLine=new boolean[curBoard.length];
		for(int i=0;i<curBoard.length;i++){
			boolean hole =false;
			for(int j=0;j<curBoard[0].length;j++){
				if(curBoard[i][j]==0) hole=true;
			}
			if(!hole){
				removeLine[i]=true;
			}
		}
		int j=0;
		for(int i=0;i<curBoard.length;i++){
			if(removeLine[i]){
				lines++;
				score+=M*level;
				continue;
			}
			else{
				for(int k=0;k<curBoard[0].length;k++){
					curBoard[j][k]=curBoard[i][k];
					boardColor[j]=boardColor[i];
				}
				j++;
			}
		}
		for(;j<curBoard.length;j++){
			for(int i=0;i<curBoard[0].length;i++){
				curBoard[j][i]=0;
			}
		}
		if(lines>=N){
			level++;
			lines=lines-N;
			FS*=1+level*S;
			timer.stop();
			timer = new Timer((int)(500/FS), this);
			timer.start();
		}
		
	}
	public boolean checkRotation(int k){
		Shape newShape=(k>0)?curShape.rotateRight():curShape.rotateLeft();
		for (int i = 0; i < 4; ++i) {
            int x = curX + newShape.x(i);
            int y = curY + newShape.y(i);
            if(x<1||x>collumn||y<1||y>row||curBoard[y-1][x-1]==1) return false;
		}
		return true;
	}
	public void checkDropped(){
		for (int i = 0; i < 4; ++i) {
            int x = curX + curShape.x(i);
            int y = curY + curShape.y(i);
            if(y==1){
            	downcompleted=true;
            	break;
            }
            else if(y<=row&&curBoard[y-2][x-1]==1){
            	downcompleted=true;
            	break;
            }
        }
	}
}
class Shape {

    enum Tetrominoes { NoShape, SShape, ZShape, MLShape, LShape, 
               SquareShape, TShape, LineShape ,n1, n2, n3, n4, n5, n6, n7, n8};

    private Tetrominoes curShape;
    private int coords[][];
    private int[][][] coordsTable;
    private int[] newshape;

    public Shape(int[] newshape) {
    	this.newshape=newshape;
        coords = new int[4][2];
        setShape(Tetrominoes.NoShape);

    }

    public void setShape(Tetrominoes shape) {

         coordsTable = new int[][][] {
            { { 0, 0 },   { 0, 0 },   { 0, 0 },   { 0, 0 } },
            { { 1, 1 },   { 2, 1 },   { 2, 2 },   { 3, 2 } },
            { { 1, 2 },   { 2, 2 },   { 2, 1 },   { 3, 1 } },
            { { 1, 1 },   { 1, 2 },   { 2, 1 },   { 3, 1 } },
            { { 1, 1 },   { 2, 1 },   { 3, 1 },   { 3, 2 } },
            { { 2, 1 },   { 2, 2 },   { 3, 1 },   { 3, 2 } },
            { { 1, 1 },   { 2, 1 },   { 3, 1 },   { 2, 2 } },
            { { 1, 1 },   { 2, 1 },   { 3, 1 },   { 4, 1 } },
            { { 1, 1 },   { 2, 1 },   { 2, 2 },   { 2, 2 } },
            { { 1, 2 },   { 2, 1 },   { 3, 1 },   { 3, 1 } },
            { { 1, 1 },   { 2, 1 },   { 3, 1 },   { 3, 1 } },
            { { 1, 1 },   { 2, 2 },   { 2, 2 },   { 2, 2 } },
            { { 1, 1 },   { 1, 2 },   { 1, 2 },   { 1, 2 } },
            { { 1, 1 },   { 2, 2 },   { 3, 1 },   { 3, 1 } },
            { { 2, 1 },   { 2, 1 },   { 2, 1 },   { 2, 1 } },
            { { 1, 1 },   { 2, 2 },   { 3, 3 },   { 2, 2 } }
        };

        for (int i = 0; i < 4 ; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        
        }
        curShape = shape;

    }
    public Color getColor(){
    	Color color;
    	switch(curShape.name()){
    		case "SShape": color=Color.yellow;break;
    		case "ZShape": color=Color.pink;break;
    		case "MLShape" : color=Color.blue;break;
    		case "LShape" : color=Color.red;break;
    		case "SquareShape" : color=Color.green;break;
    		case "TShape" : color=Color.orange;break;
    		case "LineShape" : color=Color.cyan;break;
    		case "n1" : color=Color.GRAY;break;
    		case "n2" : color=Color.GREEN;break;
    		case "n3" : color=Color.PINK;break;
    		case "n4" : color=Color.ORANGE;break;
    		case "n5" : color=Color.LIGHT_GRAY;break;
    		case "n6" : color=Color.YELLOW;break;
    		case "n7" : color=Color.BLUE;break;
    		case "n8" : color=Color.CYAN;break;
    		default: color=Color.white;   	
    	}
		return color;
    }
    public void setX(int index, int x) { coords[index][0] = x; }
    public void setY(int index, int y) { coords[index][1] = y; }
    public int x(int index) { return coords[index][0]; }
    public int y(int index) { return coords[index][1]; }
    public Tetrominoes getShape()  { return curShape; }

    public void setRandomShape()
    {
        int x = (int) (100*Math.random()% (7+newshape.length)) + 1;
        Tetrominoes[] values = Tetrominoes.values();
        if(x<8)	setShape(values[x]);
        else setShape(values[newshape[x-8]]);
    }

    public Shape rotateLeft() 
    {
        if (curShape == Tetrominoes.SquareShape)
            return this;

        Shape result = new Shape(newshape);
        result.curShape = curShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, 1+y(i));
            result.setY(i, 3-x(i));
        }
        return result;
    }

    public Shape rotateRight()
    {
        if (curShape == Tetrominoes.SquareShape)
            return this;

        Shape result = new Shape(newshape);
        result.curShape = curShape;

        for (int i = 0; i < 4; ++i) {
        	result.setX(i, 3-y(i));
            result.setY(i, x(i)-1);
        }
        return result;
    }
}
