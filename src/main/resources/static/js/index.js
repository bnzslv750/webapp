	window.onload = function(){
		const target = document.getElementById("target").value;
		let targetNum = Number(target);
		if(targetNum != 0){
			for(let i = 1; i <= 31; i++){
				const morningId = "morning_numeric" + i;
				const morning_num = document.getElementById(morningId).value;
				let morningNum = Number(morning_num);
				if(morningNum < targetNum - 50 && morningNum != 0){
					const morningFormId = "morningForm" + i;
					const morningForm = document.getElementById(morningFormId);
					morningForm.classList.add('targetBlue');
				} else if(morningNum > targetNum + 50){
					const morningFormId = "morningForm" + i;
					const morningForm = document.getElementById(morningFormId);
					morningForm.classList.add('targetRed');
				}
				const noonId = "noon_numeric" + i;
				const noon_num = document.getElementById(noonId).value;
				let noonNum = Number(noon_num);
				if(noonNum < targetNum - 50 && noonNum != 0){
					const noonFormId = "noonForm" + i;
					const noonForm = document.getElementById(noonFormId);
					noonForm.classList.add('targetBlue');
				} else if(noonNum > targetNum + 50){
					const noonFormId = "noonForm" + i;
					const noonForm = document.getElementById(noonFormId);
					noonForm.classList.add('targetRed');
				}
				const eveningId = "evening_numeric" + i;
				const evening_num = document.getElementById(eveningId).value;
				let eveningNum = Number(evening_num);
				if(eveningNum < targetNum - 50 && eveningNum != 0){
					const eveningFormId = "eveningForm" + i;
					const eveningForm = document.getElementById(eveningFormId);
					eveningForm.classList.add('targetBlue');
				} else if(eveningNum > targetNum + 50){
					const eveningFormId = "eveningForm" + i;
					const eveningForm = document.getElementById(eveningFormId);
					eveningForm.classList.add('targetRed');
				}
			}
		}

	}