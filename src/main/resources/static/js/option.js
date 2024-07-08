	window.onload = function(){
		const target = document.getElementById("target").value;
		let targetNum = Number(target);
		if(targetNum != 0){
			for(let i = 1; i <= 31; i++){
				const morningId = "hidmorning_numeric" + i;
				const morning_num = document.getElementById(morningId).value;
				let morningNum = Number(morning_num);
				if(morningNum < targetNum - 50 && morningNum != 0){
					// 朝食
					const breakfastId = "breakfast" + i;
					const breakfast = document.getElementById(breakfastId);
					breakfast.classList.add('targetBlue');
					// 朝のインスリン
					const morningInsulinId = "morning_insulin" + i;
					const morningInsulin = document.getElementById(morningInsulinId);
					morningInsulin.classList.add('targetBlue');
					// 朝の血糖値
					const morningNumId = "morning_numeric" + i;
					const morningNumeric = document.getElementById(morningNumId);
					morningNumeric.classList.add('targetBlue');
				} else if(morningNum > targetNum + 50){
					// 朝食
					const breakfastId = "breakfast" + i;
					const breakfast = document.getElementById(breakfastId);
					breakfast.classList.add('targetRed');
					// 朝のインスリン
					const morningInsulinId = "morning_insulin" + i;
					const morningInsulin = document.getElementById(morningInsulinId);
					morningInsulin.classList.add('targetRed');
					// 朝の血糖値
					const morningNumId = "morning_numeric" + i;
					const morningNumeric = document.getElementById(morningNumId);
					morningNumeric.classList.add('targetRed');
				}
				const noonId = "hidnoon_numeric" + i;
				const noon_num = document.getElementById(noonId).value;
				let noonNum = Number(noon_num);
				if(noonNum < targetNum - 50 && noonNum != 0){
					// 昼食
					const lunchId = "lunch" + i;
					const lunch = document.getElementById(lunchId);
					lunch.classList.add('targetBlue');
					// 昼のインスリン
					const noonInsulinId = "noon_insulin" + i;
					const noonInsulin = document.getElementById(noonInsulinId);
					noonInsulin.classList.add('targetBlue');
					// 昼の血糖値
					const noonNumId = "noon_numeric" + i;
					const noonNumeric = document.getElementById(noonNumId);
					noonNumeric.classList.add('targetBlue');
				} else if(noonNum > targetNum + 50){
					// 昼食
					const lunchId = "lunch" + i;
					const lunch = document.getElementById(lunchId);
					lunch.classList.add('targetRed');
					// 昼のインスリン
					const noonInsulinId = "noon_insulin" + i;
					const noonInsulin = document.getElementById(noonInsulinId);
					noonInsulin.classList.add('targetRed');
					// 昼の血糖値
					const noonNumId = "noon_numeric" + i;
					const noonNumeric = document.getElementById(noonNumId);
					noonNumeric.classList.add('targetRed');
				}
				const eveningId = "hidevening_numeric" + i;
				const evening_num = document.getElementById(eveningId).value;
				let eveningNum = Number(evening_num);
				if(eveningNum < targetNum - 50 && eveningNum != 0){
					// 夕食
					const dinnerId = "dinner" + i;
					const dinner = document.getElementById(dinnerId);
					dinner.classList.add('targetBlue');
					// 夕方のインスリン
					const dinnerInsulinId = "evening_insulin" + i;
					const dinnerInsulin = document.getElementById(dinnerInsulinId);
					dinnerInsulin.classList.add('targetBlue');
					// 夕方の血糖値
					const eveningNumId = "evening_numeric" + i;
					const eveningNumeric = document.getElementById(eveningNumId);
					eveningNumeric.classList.add('targetBlue');
				} else if(eveningNum > targetNum + 50){
					// 夕食
					const dinnerId = "dinner" + i;
					const dinner = document.getElementById(dinnerId);
					dinner.classList.add('targetRed');
					// 夕方のインスリン
					const dinnerInsulinId = "evening_insulin" + i;
					const dinnerInsulin = document.getElementById(dinnerInsulinId);
					dinnerInsulin.classList.add('targetRed');
					// 夕方の血糖値
					const eveningNumId = "evening_numeric" + i;
					const eveningNumeric = document.getElementById(eveningNumId);
					eveningNumeric.classList.add('targetRed');
				}
			}
		}

	}