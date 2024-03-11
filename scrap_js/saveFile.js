const fs = require('fs'); 



class SaveFile {

	
	constructor(filePath) {
		this.filePath = filePath;
		this.borrar();
	}
	
	borrar() {
		try {
			if (fs.existsSync(this.filePath)) {
			  fs.writeFileSync(this.filePath, '');
			}
		} catch (error) {
			console.log("Fallo al borrar el archivo");
		}
	  }
	
	  guardar(listaObjetos) {
		let listaObjetosExistente = [];
		try {
			const contenidoArchivo = fs.readFileSync(this.filePath, 'utf-8');
			if (contenidoArchivo.length > 0){
				listaObjetosExistente = JSON.parse(contenidoArchivo);
			}
		} catch (error) {
			console.log("Fallo al leer el archivo");
		}
		  const listaObjetosFinal = listaObjetosExistente.concat(listaObjetos);
		  
		  const contenidoJSON = JSON.stringify(listaObjetosFinal, null, 2);
		// const contenidoJSON = JSON.stringify(listaObjetos);
		fs.writeFileSync(this.filePath, contenidoJSON);
		console.log('Objetos guardados correctamente.');
	  }

	// borrar() {
	// 	fs.truncate(filePath, 0, (err) => {
	// 		if (err) {
	// 			console.error('Error al borrar contenido:', err);
	// 			return;
	// 		}
	// 		console.log('Contenido del archivo borrado.');
	// 	});
	// }

	// guardar(objetos) {
	// 	fs.writeFile(filePath, JSON.stringify(objetos, null, 2), (err) => {
	// 		if (err) {
	// 			console.error('Error al guardar objetos:', err);
	// 			return;
	// 		}
	// 		console.log('Objetos guardados correctamente.');
	// 	});
	// }


}

module.exports = SaveFile;