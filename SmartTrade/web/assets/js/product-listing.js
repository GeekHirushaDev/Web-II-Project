var modelList;

async function loadProductData() {

    const response = await fetch(
            "LoadProductData"
            );

    if (response.ok) {

        const json = await response.json();
        if (json.status) {

            //load models 
//            const modelSelect = document.getElementById("model");
//            json.modelList.forEach(item => {
//
//                const modelOption = document.createElement("option");
//                modelOption.value = item.id;
//                modelOption.innerHTML = item.name;
//                modelSelect.appendChild(modelOption);
//            });

            loadSelect("brand", json.brandList, "name");
            modelList = json.modelList;
            //loadSelect("model",json.modelList,"name");
            loadSelect("storage", json.storageList, "value");
            loadSelect("color", json.colorList, "value");
            loadSelect("condition", json.qualityList, "value");

        } else {
            document.getElementById("message").innerHTML = "Unable to get product data! Please try again later";
        }

    } else {
        document.getElementById("message").innerHTML = "Unable to get product data! Please try again later";
    }

}

function loadSelect(selectId, list, property) {

    const select = document.getElementById(selectId);

    list.forEach(item => {

        const option = document.createElement("option");
        option.value = item.id;
        option.innerHTML = item[property];
        select.appendChild(option);

    });

}

function loadModels() {

    const brandId = document.getElementById("brand").value;
    const modelSelect = document.getElementById("model");
    modelSelect.length = 1;

    modelList.forEach(item => {
        if (item.brand.id == brandId) { // == -> Consider only the value, === -> Considers both the value and the data type
            const option = document.createElement("option");
            option.value = item.id;
            option.innerHTML = item.name;
            modelSelect.appendChild(option);
        }
    });

}

async function saveProduct() {

    const brandId = document.getElementById("brand").value;
    const modelId = document.getElementById("model").value;
    const title = document.getElementById("title").value;
    const description = document.getElementById("description").value;
    const storageId = document.getElementById("storage").value;
    const colorId = document.getElementById("color").value;
    const conditionId = document.getElementById("condition").value;
    const price = document.getElementById("price").value;
    const qty = document.getElementById("qty").value;

    const img1 = document.getElementById("img1").files[0];
    const img2 = document.getElementById("img2").files[0];
    const img3 = document.getElementById("img3").files[0];

    const form = new FormData();
    form.append("brandId", brandId);
    form.append("modelId", modelId);
    form.append("title", title);
    form.append("description", description);
    form.append("storageId", storageId);
    form.append("colorId", colorId);
    form.append("conditionId", conditionId);
    form.append("price", price);
    form.append("qty", qty);
    form.append("img1", img1);
    form.append("img2", img2);
    form.append("img3", img3);

    const response = await fetch(
            "SaveProduct",
            {
                method: "POST",
                body: form
            }
    );

}