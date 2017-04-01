package myUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import excecoes.ESException;

public class EntradaSaidaDados {
	
	public static void geraRelatorio(String caminho, String relatorio) throws ESException {
		FileWriter fileWriter = null;
		PrintWriter printWriter = null;
		
		try {
			fileWriter = new FileWriter(caminho);
			printWriter = new PrintWriter(fileWriter);
		}
		catch(IOException e) {
			throw new ESException("erro ao estabelecer conexao com pasta de saida");
		}

		if(printWriter != null) {
			printWriter.print(relatorio);
		}
		printWriter.close();
	}

	public static void escreveObjeto(String caminho, Object obj) throws IOException {
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;
		
		try {
			fileOutput = new FileOutputStream(caminho);
			objectOutput = new ObjectOutputStream(fileOutput);
		}
		catch(FileNotFoundException e) {
			throw new ESException("Arquivo nao encontrado");
		}
		catch(IOException e) {
			throw new ESException();
		}
		
		if(objectOutput != null) {
			objectOutput.writeObject(obj);
		}

		objectOutput.close();
	}
	
	public static Object leObjeto(String caminho) throws ClassNotFoundException, IOException {
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		Object obj = null;
		
		try {
			fileInput = new FileInputStream(caminho);
			objectInput = new ObjectInputStream(fileInput);
		}
		catch(FileNotFoundException e) {
			throw new ESException("Arquivo nao encontrado");
		}
		catch(IOException e) {
			throw new ESException();
		}
		
		if(objectInput != null) {
			obj = objectInput.readObject();
		}

		objectInput.close();
		return obj;
	}
}