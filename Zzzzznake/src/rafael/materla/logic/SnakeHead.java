package rafael.materla.logic;

public class SnakeHead extends Figure {

	
	void moveUp(){
		setPosition(position.x, position.y - 1);
	}
	
	void moveRight(){
		setPosition(position.x + 1, position.y);
	}
	
	void moveDown(){
		setPosition(position.x, position.y + 1);
	}
	
	void moveLeft(){
		setPosition(position.x - 1, position.y);
	}

	@Override
	void onCollision() {
		// TODO Auto-generated method stub
		
	}

}
