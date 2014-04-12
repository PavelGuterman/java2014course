package model;

public class ModelFactory {
	private interface Creator {
		public Model create();
	}

	private class DomainCreator implements Creator {
		public Model create() {
			return new Domain;
		}
	}

}
