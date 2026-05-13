package com.pratham.chikitse.data

import androidx.compose.ui.graphics.Color
import com.pratham.chikitse.ui.theme.BurnColor
import com.pratham.chikitse.ui.theme.ChokingColor
import com.pratham.chikitse.ui.theme.SnakeBiteColor

data class EmergencyType(
    val id: String,
    val titleEn: String,
    val titleKn: String,
    val emoji: String,
    val color: Color,
    val descriptionEn: String,
    val descriptionKn: String
)

data class FirstAidStep(
    val stepNumber: Int,
    val titleEn: String,
    val titleKn: String,
    val descriptionEn: String,
    val descriptionKn: String,
    val emoji: String
)

data class DosDontsItem(
    val textEn: String,
    val textKn: String,
    val isDo: Boolean
)

data class Hospital(
    val name: String,
    val district: String,
    val address: String,
    val phone: String,
    val type: String
)

object EmergencyDataSource {

    val emergencies = listOf(
        EmergencyType(
            id = "snakebite",
            titleEn = "Snake Bite",
            titleKn = "ಹಾವು ಕಡಿತ",
            emoji = "🐍",
            color = SnakeBiteColor,
            descriptionEn = "Venomous snake bites require immediate calm action. Do NOT panic.",
            descriptionKn = "ವಿಷಕಾರಿ ಹಾವು ಕಡಿತಕ್ಕೆ ತಕ್ಷಣ ಶಾಂತವಾಗಿ ಕ್ರಮ ಕೈಗೊಳ್ಳಿ."
        ),
        EmergencyType(
            id = "burn",
            titleEn = "Burns",
            titleKn = "ಸುಟ್ಟಗಾಯ",
            emoji = "🔥",
            color = BurnColor,
            descriptionEn = "Cool the burn immediately with running water for 10–20 minutes.",
            descriptionKn = "10-20 ನಿಮಿಷ ತಣ್ಣೀರಿನಿಂದ ಸುಟ್ಟ ಭಾಗವನ್ನು ತಂಪುಗೊಳಿಸಿ."
        ),
        EmergencyType(
            id = "choking",
            titleEn = "Choking",
            titleKn = "ಗಂಟಲಿನಲ್ಲಿ ಅಡಚಣೆ",
            emoji = "🫁",
            color = ChokingColor,
            descriptionEn = "Choking is life-threatening. Act within seconds using back blows or Heimlich.",
            descriptionKn = "ಗಂಟಲಿನಲ್ಲಿ ಅಡಚಣೆ ಜೀವಕ್ಕೆ ಅಪಾಯ. ತಕ್ಷಣ ಕ್ರಮ ತೆಗೆದುಕೊಳ್ಳಿ."
        ),
        EmergencyType(
            id = "heartattack",
            titleEn = "Heart Attack",
        titleKn = "ಹೃದಯಾಘಾತ",
        emoji = "❤️",
        color = Color(0xFFC62828),
        descriptionEn = "Recognize and respond to heart attack symptoms immediately.",
        descriptionKn = "ಹೃದಯಾಘಾತ ಲಕ್ಷಣಗಳನ್ನು ತಕ್ಷಣ ಗುರುತಿಸಿ ಕ್ರಮ ತೆಗೆಯಿರಿ."
    ),
    EmergencyType(
    id = "bleeding",
    titleEn = "Severe Bleeding",
    titleKn = "ತೀವ್ರ ರಕ್ತಸ್ರಾವ",
    emoji = "🩸",
    color = Color(0xFFB71C1C),
    descriptionEn = "Control severe bleeding immediately to prevent blood loss.",
    descriptionKn = "ರಕ್ತನಷ್ಟ ತಡೆಯಲು ತಕ್ಷಣ ರಕ್ತಸ್ರಾವ ನಿಯಂತ್ರಿಸಿ."
    ),
    EmergencyType(
    id = "fracture",
    titleEn = "Fracture",
    titleKn = "ಮೂಳೆ ಮುರಿತ",
    emoji = "🦴",
    color = Color(0xFF37474F),
    descriptionEn = "Immobilize broken bones and prevent further injury.",
    descriptionKn = "ಮುರಿದ ಮೂಳೆ ಸ್ಥಿರಗೊಳಿಸಿ, ಹೆಚ್ಚಿನ ಗಾಯ ತಡೆಯಿರಿ."
    ),
    EmergencyType(
    id = "drowning",
    titleEn = "Drowning",
    titleKn = "ನೀರಿನಲ್ಲಿ ಮುಳುಗುವಿಕೆ",
    emoji = "🌊",
    color = Color(0xFF1565C0),
    descriptionEn = "Act fast to rescue and revive a drowning person.",
    descriptionKn = "ಮುಳುಗುತ್ತಿರುವ ವ್ಯಕ್ತಿಯನ್ನು ತ್ವರಿತವಾಗಿ ರಕ್ಷಿಸಿ."
    ),
    EmergencyType(
    id = "poisoning",
    titleEn = "Poisoning",
    titleKn = "ವಿಷಪ್ರಾಶನ",
    emoji = "☠️",
    color = Color(0xFF4A148C),
    descriptionEn = "Identify poison type and take correct immediate action.",
    descriptionKn = "ವಿಷದ ಪ್ರಕಾರ ಗುರುತಿಸಿ ಸರಿಯಾದ ಕ್ರಮ ತೆಗೆಯಿರಿ."
    ),
    EmergencyType(
    id = "heatstroke",
    titleEn = "Heat Stroke",
    titleKn = "ಶಾಖದ ಹೊಡೆತ",
    emoji = "🌡️",
    color = Color(0xFFE65100),
    descriptionEn = "Cool the person down rapidly. Heat stroke is life-threatening.",
    descriptionKn = "ತ್ವರಿತವಾಗಿ ತಂಪುಗೊಳಿಸಿ. ಶಾಖದ ಹೊಡೆತ ಅಪಾಯಕಾರಿ."
    ),
    EmergencyType(
    id = "seizure",
    titleEn = "Seizure / Epilepsy",
    titleKn = "ಮೂರ್ಛೆ ರೋಗ",
    emoji = "🧠",
    color = Color(0xFF00695C),
    descriptionEn = "Keep the person safe during and after a seizure.",
    descriptionKn = "ಮೂರ್ಛೆ ಸಮಯದಲ್ಲಿ ಮತ್ತು ನಂತರ ವ್ಯಕ್ತಿಯನ್ನು ಸುರಕ್ಷಿತವಾಗಿ ಇರಿಸಿ."
    )
    )

    val steps = mapOf(
        "snakebite" to listOf(
            FirstAidStep(1, "Stay Calm", "ಶಾಂತರಾಗಿರಿ",
                "Keep the victim calm and still. Panic increases heart rate and spreads venom faster through the bloodstream.",
                "ಸಂತ್ರಸ್ತರನ್ನು ಶಾಂತವಾಗಿ ಮತ್ತು ಸ್ಥಿರವಾಗಿ ಇರಿಸಿ. ಭಯ ಹೃದಯ ಬಡಿತ ಹೆಚ್ಚಿಸುತ್ತದೆ.", "😮‍💨"),
            FirstAidStep(2, "Immobilise the Limb", "ಅಂಗವನ್ನು ಸ್ಥಿರಗೊಳಿಸಿ",
                "Keep the bitten limb still and below the level of the heart. Use a makeshift splint if needed to prevent movement.",
                "ಕಡಿದ ಅಂಗವನ್ನು ಹೃದಯ ಮಟ್ಟಕ್ಕಿಂತ ಕೆಳಗೆ ಇರಿಸಿ.", "🦵"),
            FirstAidStep(3, "Remove Tight Items", "ತಗ್ಗಿದ ವಸ್ತುಗಳನ್ನು ತೆಗೆಯಿರಿ",
                "Remove rings, watches, tight clothing near the bite. Swelling may occur rapidly.",
                "ಉಂಗುರ, ಗಡಿಯಾರ, ಬಿಗಿ ಬಟ್ಟೆ ತೆಗೆಯಿರಿ. ಊತ ಬೇಗನೇ ಬರಬಹುದು.", "💍"),
            FirstAidStep(4, "Note the Snake", "ಹಾವಿನ ವಿವರ ನಿಗಾ ಇರಿಸಿ",
                "Try to remember the snake's color, size, and pattern. Do NOT try to catch or kill it. This helps doctors choose the right antivenom.",
                "ಹಾವಿನ ಬಣ್ಣ, ಗಾತ್ರ ನೆನಪಿಡಿ. ಅದನ್ನು ಹಿಡಿಯಲು ಹೋಗಬೇಡಿ.", "🔍"),
            FirstAidStep(5, "Rush to Hospital", "ತಕ್ಷಣ ಆಸ್ಪತ್ರೆಗೆ ಹೋಗಿ",
                "Transport the victim to the nearest hospital IMMEDIATELY. Antivenom must be administered by medical professionals.",
                "ಸಂತ್ರಸ್ತರನ್ನು ತಕ್ಷಣ ಹತ್ತಿರದ ಆಸ್ಪತ್ರೆಗೆ ಕರೆದೊಯ್ಯಿರಿ.", "🏥")
        ),
        "burn" to listOf(
            FirstAidStep(1, "Cool with Water", "ನೀರಿನಿಂದ ತಂಪುಗೊಳಿಸಿ",
                "Hold the burned area under cool (not cold) running water for 10–20 minutes. This stops the burning process and reduces pain.",
                "10-20 ನಿಮಿಷ ತಣ್ಣೀರಿನಲ್ಲಿ ಸುಟ್ಟ ಭಾಗ ಇಡಿ.", "💧"),
            FirstAidStep(2, "Remove Clothing", "ಬಟ್ಟೆ ತೆಗೆಯಿರಿ",
                "Gently remove clothing and jewelry from the burned area UNLESS they are stuck to the skin. Do not pull stuck material.",
                "ಸುಟ್ಟ ಭಾಗದ ಬಟ್ಟೆ ಮೃದುವಾಗಿ ತೆಗೆಯಿರಿ. ಅಂಟಿದ್ದರೆ ಎಳೆಯಬೇಡಿ.", "👕"),
            FirstAidStep(3, "Cover the Burn", "ಸುಟ್ಟಗಾಯ ಮುಚ್ಚಿ",
                "Cover loosely with a clean, non-fluffy material like cling film or a clean plastic bag. Never use cotton wool directly.",
                "ಸ್ವಚ್ಛ, ಅಂಟದ ಬಟ್ಟೆಯಿಂದ ಸಡಿಲವಾಗಿ ಮುಚ್ಚಿ.", "🩹"),
            FirstAidStep(4, "Assess Severity", "ತೀವ್ರತೆ ಅಳೆಯಿರಿ",
                "Minor burns: small, red, not blistered → treat at home. Severe: large, blistered, white/charred → go to hospital immediately.",
                "ಚಿಕ್ಕ ಸುಟ್ಟಗಾಯ: ಮನೆಯಲ್ಲಿ ಚಿಕಿತ್ಸೆ. ದೊಡ್ಡ ಸುಟ್ಟಗಾಯ: ಆಸ್ಪತ್ರೆಗೆ ಹೋಗಿ.", "🔎"),
            FirstAidStep(5, "Seek Medical Help", "ವೈದ್ಯಕೀಯ ಸಹಾಯ ಪಡೆಯಿರಿ",
                "For burns larger than your palm, on face/hands/joints, or if in doubt — go to the hospital immediately.",
                "ಅಂಗೈ ಗಾತ್ರಕ್ಕಿಂತ ದೊಡ್ಡ ಸುಟ್ಟಗಾಯಕ್ಕೆ ತಕ್ಷಣ ಆಸ್ಪತ್ರೆ ಸೇರಿ.", "🏥")
        ),
        "choking" to listOf(
            FirstAidStep(1, "Identify Choking", "ಗಂಟಲಿನ ಅಡಚಣೆ ಗುರುತಿಸಿ",
                "Signs: unable to speak, cough, or breathe; high-pitched noise; turning blue. Ask 'Are you choking?' If they can't answer, act immediately.",
                "ಮಾತಾಡಲು, ಕೆಮ್ಮಲು ಅಸಾಧ್ಯ; ಮುಖ ನೀಲಿ ಆಗುವುದು - ತಕ್ಷಣ ಕ್ರಮ ತೆಗೆಯಿರಿ.", "⚠️"),
            FirstAidStep(2, "5 Back Blows", "5 ಬೆನ್ನಿನ ಏಟು",
                "Lean the person forward. Give 5 firm blows between the shoulder blades with the heel of your hand.",
                "ಮುಂದಕ್ಕೆ ಬಗ್ಗಿಸಿ. ಭುಜದ ಮಧ್ಯೆ 5 ಗಟ್ಟಿ ಏಟು ಕೊಡಿ.", "👊"),
            FirstAidStep(3, "5 Abdominal Thrusts", "5 ಹೊಟ್ಟೆ ಒತ್ತಡ (Heimlich)",
                "Stand behind the person. Make a fist just above the navel. Grasp with other hand. Pull sharply inward and upward 5 times.",
                "ಹಿಂದೆ ನಿಂತು, ಹೊಕ್ಕಳ ಮೇಲೆ ಮುಷ್ಟಿ ಇಟ್ಟು 5 ಬಾರಿ ಒಳ-ಮೇಲ್ಮುಖ ಒತ್ತಿ.", "🤜"),
            FirstAidStep(4, "Repeat Cycle", "ಪುನರಾವರ್ತಿಸಿ",
                "Alternate 5 back blows and 5 abdominal thrusts. Continue until the object is expelled or the person loses consciousness.",
                "5 ಬೆನ್ನಿನ ಏಟು ಮತ್ತು 5 ಹೊಟ್ಟೆ ಒತ್ತಡ ಪರ್ಯಾಯವಾಗಿ ಮಾಡಿ.", "🔄"),
            FirstAidStep(5, "Call for Help", "ಸಹಾಯ ಕರೆಯಿರಿ",
                "Call 108 immediately. If person becomes unconscious, begin CPR. For infants, use gentler back blows and 2-finger chest thrusts.",
                "108 ಕರೆ ಮಾಡಿ. ಮೂರ್ಛೆ ಹೋದರೆ CPR ಶುರು ಮಾಡಿ.", "📞")
        ),
        "heartattack" to listOf(
            FirstAidStep(1, "Call 108 Immediately", "ತಕ್ಷಣ 108 ಕರೆ ಮಾಡಿ",
                "Call emergency services immediately. Do not drive the patient yourself unless absolutely necessary.",
                "ತಕ್ಷಣ ತುರ್ತು ಸೇವೆ ಕರೆ ಮಾಡಿ. ಸಾಧ್ಯವಾದಷ್ಟು ರೋಗಿಯನ್ನು ನೀವೇ ಕರೆದೊಯ್ಯಬೇಡಿ.", "📞"),
            FirstAidStep(2, "Make Them Rest", "ವಿಶ್ರಾಂತಿ ತೆಗೆಯಿರಿ",
                "Help the person sit or lie down in a comfortable position. Loosen any tight clothing around neck and chest.",
                "ವ್ಯಕ್ತಿಯನ್ನು ಆರಾಮದಾಯಕ ಸ್ಥಿತಿಯಲ್ಲಿ ಕೂರಿಸಿ ಅಥವಾ ಮಲಗಿಸಿ.", "🛋️"),
            FirstAidStep(3, "Give Aspirin If Available", "ಆಸ್ಪಿರಿನ್ ಕೊಡಿ",
                "If the person is not allergic and is conscious, give one adult aspirin (325mg) to chew — not swallow whole.",
                "ಅಲರ್ಜಿ ಇಲ್ಲದಿದ್ದರೆ ಒಂದು ಆಸ್ಪಿರಿನ್ ಮಾತ್ರೆ ಜಗಿಯಲು ಕೊಡಿ.", "💊"),
            FirstAidStep(4, "Monitor Breathing", "ಉಸಿರಾಟ ಗಮನಿಸಿ",
                "Watch breathing constantly. If the person stops breathing and becomes unresponsive, begin CPR immediately.",
                "ಉಸಿರಾಟ ನಿಂತರೆ ತಕ್ಷಣ CPR ಪ್ರಾರಂಭಿಸಿ.", "👁️"),
            FirstAidStep(5, "Begin CPR If Needed", "CPR ಮಾಡಿ",
                "Push hard and fast in the center of the chest — 100-120 compressions per minute. Continue until help arrives.",
                "ಎದೆ ಮಧ್ಯಭಾಗದಲ್ಲಿ ಗಟ್ಟಿ ಒತ್ತಡ ನೀಡಿ. ಸಹಾಯ ಬರುವವರೆಗೆ ಮಾಡಿ.", "🫀")
        ),
        "bleeding" to listOf(
            FirstAidStep(1, "Apply Direct Pressure", "ನೇರ ಒತ್ತಡ ಹಾಕಿ",
                "Use a clean cloth or bandage and press firmly on the wound. Do not remove the cloth even if soaked — add more on top.",
                "ಸ್ವಚ್ಛ ಬಟ್ಟೆಯಿಂದ ಗಾಯದ ಮೇಲೆ ಗಟ್ಟಿಯಾಗಿ ಒತ್ತಿ ಹಿಡಿಯಿರಿ.", "🩹"),
            FirstAidStep(2, "Elevate the Wound", "ಗಾಯ ಎತ್ತರಿಸಿ",
                "Raise the injured area above the level of the heart to slow blood flow, if possible.",
                "ಗಾಯದ ಭಾಗ ಹೃದಯ ಮಟ್ಟಕ್ಕಿಂತ ಮೇಲೆ ಇರಿಸಿ.", "⬆️"),
            FirstAidStep(3, "Do Not Remove Object", "ವಸ್ತು ತೆಗೆಯಬೇಡಿ",
                "If an object is embedded in the wound, do NOT remove it. Press around it and stabilize it.",
                "ಗಾಯದಲ್ಲಿ ವಸ್ತು ಸಿಕ್ಕಿದ್ದರೆ ತೆಗೆಯಬೇಡಿ. ಸುತ್ತ ಒತ್ತಿ ಹಿಡಿಯಿರಿ.", "⚠️"),
            FirstAidStep(4, "Keep Person Warm", "ಬೆಚ್ಚಗಿರಿಸಿ",
                "Cover the person with a blanket to prevent shock. Keep them calm and still.",
                "ಶಾಕ್ ತಡೆಯಲು ಹೊದಿಕೆ ಹೊದಿಸಿ. ಶಾಂತವಾಗಿ ಮಲಗಿಸಿ.", "🛡️"),
            FirstAidStep(5, "Seek Medical Help", "ವೈದ್ಯಕೀಯ ಸಹಾಯ",
                "For deep or large wounds, call 108. Do not attempt to stitch or close wounds yourself.",
                "ದೊಡ್ಡ ಗಾಯಕ್ಕೆ 108 ಕರೆ ಮಾಡಿ. ಗಾಯ ಹೊಲೆಯಲು ಪ್ರಯತ್ನಿಸಬೇಡಿ.", "🏥")
        ),
        "fracture" to listOf(
            FirstAidStep(1, "Do Not Move the Person", "ವ್ಯಕ್ತಿಯನ್ನು ಅಲ್ಲಾಡಿಸಬೇಡಿ",
                "Keep the injured area still. Moving a fracture can cause more damage or internal bleeding.",
                "ಮುರಿದ ಭಾಗ ಅಲ್ಲಾಡಿಸಬೇಡಿ. ಚಲನೆ ಹೆಚ್ಚಿನ ಗಾಯ ಉಂಟು ಮಾಡಬಹುದು.", "🚫"),
            FirstAidStep(2, "Immobilise the Limb", "ಅಂಗ ಸ್ಥಿರಗೊಳಿಸಿ",
                "Support the limb in the position found. Use a splint, rolled newspaper, or sturdy stick padded with cloth.",
                "ಸಿಕ್ಕಿದ ಸ್ಥಿತಿಯಲ್ಲೇ ಅಂಗ ಆಧರಿಸಿ. ಸ್ಪ್ಲಿಂಟ್ ಅಥವಾ ಗಟ್ಟಿ ಕಡ್ಡಿ ಬಳಸಿ.", "🦯"),
            FirstAidStep(3, "Apply Ice Pack", "ಐಸ್ ಹಾಕಿ",
                "Apply ice wrapped in cloth to reduce swelling. Never apply ice directly to skin.",
                "ಬಟ್ಟೆಯಲ್ಲಿ ಸುತ್ತಿದ ಐಸ್ ಹಾಕಿ ಊತ ಕಡಿಮೆ ಮಾಡಿ. ನೇರ ಐಸ್ ಹಾಕಬೇಡಿ.", "🧊"),
            FirstAidStep(4, "Check Circulation", "ರಕ್ತಸಂಚಾರ ಪರೀಕ್ಷಿಸಿ",
                "Check that the bandage is not too tight. Look for numbness, tingling, or color changes beyond the injury.",
                "ಬ್ಯಾಂಡೇಜ್ ಬಿಗಿಯಾಗಿಲ್ಲ ಎಂದು ಖಚಿತಪಡಿಸಿ. ಮರಗಟ್ಟುವಿಕೆ ಗಮನಿಸಿ.", "🔍"),
            FirstAidStep(5, "Seek Medical Help", "ವೈದ್ಯಕೀಯ ಸಹಾಯ",
                "All suspected fractures must be seen by a doctor and X-rayed. Call 108 for spine or pelvis injuries.",
                "ಎಲ್ಲಾ ಮೂಳೆ ಮುರಿತಕ್ಕೆ ವೈದ್ಯರನ್ನು ಕಾಣಿ. ಬೆನ್ನುಮೂಳೆ ಗಾಯಕ್ಕೆ 108 ಕರೆ ಮಾಡಿ.", "🏥")
        ),
        "drowning" to listOf(
            FirstAidStep(1, "Ensure Your Safety", "ನಿಮ್ಮ ಸುರಕ್ಷತೆ ಖಚಿತಪಡಿಸಿ",
                "Do NOT jump in unless trained. Throw a rope, life ring, or extend a pole. Shout for help.",
                "ತರಬೇತಿ ಇಲ್ಲದಿದ್ದರೆ ನೀರಿಗೆ ಹಾರಬೇಡಿ. ಹಗ್ಗ ಅಥವಾ ಕೋಲು ಎಸೆಯಿರಿ.", "⚠️"),
            FirstAidStep(2, "Remove From Water", "ನೀರಿನಿಂದ ಹೊರ ತೆಗೆಯಿರಿ",
                "Once safe, carefully remove the person from water supporting the head and neck.",
                "ಸುರಕ್ಷಿತವಾಗಿ ತಲೆ ಮತ್ತು ಕತ್ತು ಆಧರಿಸಿ ನೀರಿನಿಂದ ಹೊರ ತೆಗೆಯಿರಿ.", "🏊"),
            FirstAidStep(3, "Check Breathing", "ಉಸಿರಾಟ ಪರೀಕ್ಷಿಸಿ",
                "Tilt head back gently and listen for breathing for no more than 10 seconds.",
                "ತಲೆ ಮೃದುವಾಗಿ ಹಿಂದಕ್ಕೆ ವಾಲಿಸಿ 10 ಸೆಕೆಂಡ್ ಉಸಿರಾಟ ಕೇಳಿ.", "👂"),
            FirstAidStep(4, "Begin CPR", "CPR ಪ್ರಾರಂಭಿಸಿ",
                "If not breathing: give 5 rescue breaths then 30 chest compressions. Repeat until breathing or help arrives.",
                "ಉಸಿರಾಟ ಇಲ್ಲದಿದ್ದರೆ 5 ಉಸಿರು ನೀಡಿ, ನಂತರ 30 ಎದೆ ಒತ್ತಡ. ಮತ್ತೆ ಮತ್ತೆ ಮಾಡಿ.", "🫁"),
            FirstAidStep(5, "Call 108", "108 ಕರೆ ಮಾಡಿ",
                "All drowning victims need medical evaluation even if they seem fine. Internal complications can develop.",
                "ಚೇತರಿಸಿಕೊಂಡರೂ ಎಲ್ಲಾ ಮುಳುಗಿದ ವ್ಯಕ್ತಿಗಳಿಗೆ ವೈದ್ಯಕೀಯ ತಪಾಸಣೆ ಅಗತ್ಯ.", "📞")
        ),
        "poisoning" to listOf(
            FirstAidStep(1, "Identify the Poison", "ವಿಷ ಗುರುತಿಸಿ",
                "Try to find out what was swallowed, inhaled, or absorbed. Keep the container for doctors.",
                "ಏನು ನುಂಗಿದರು, ಉಸಿರಾಡಿದರು ಅಥವಾ ಸ್ಪರ್ಶಿಸಿದರು ಎಂದು ತಿಳಿಯಿರಿ.", "🔍"),
            FirstAidStep(2, "Call Poison Helpline", "ವಿಷ ಸಹಾಯವಾಣಿ ಕರೆ ಮಾಡಿ",
                "Call 1800-116-117 (Poison Control India) or 108. Do NOT induce vomiting unless specifically told to.",
                "1800-116-117 ಅಥವಾ 108 ಕರೆ ಮಾಡಿ. ವಾಂತಿ ಮಾಡಿಸಬೇಡಿ.", "📞"),
            FirstAidStep(3, "Fresh Air for Inhalation", "ತಾಜಾ ಗಾಳಿ",
                "If poison was inhaled, move the person to fresh air immediately. Stay upwind of the source.",
                "ಉಸಿರಿನ ಮೂಲಕ ವಿಷ ಹೋದರೆ ತಕ್ಷಣ ತಾಜಾ ಗಾಳಿಗೆ ಕರೆದೊಯ್ಯಿರಿ.", "💨"),
            FirstAidStep(4, "Skin Contact — Wash", "ಚರ್ಮ ಸಂಪರ್ಕ — ತೊಳೆಯಿರಿ",
                "If poison touched skin or eyes, rinse with large amounts of clean water for at least 15–20 minutes.",
                "ಚರ್ಮ ಅಥವಾ ಕಣ್ಣಿಗೆ ತಾಕಿದ್ದರೆ 15-20 ನಿಮಿಷ ಸ್ವಚ್ಛ ನೀರಿನಿಂದ ತೊಳೆಯಿರಿ.", "🚿"),
            FirstAidStep(5, "Rush to Hospital", "ಆಸ್ಪತ್ರೆಗೆ ಹೋಗಿ",
                "Take the poison container or note the substance name. This helps doctors administer the correct antidote.",
                "ವಿಷದ ಡಬ್ಬಿ ತೆಗೆದುಕೊಂಡು ಆಸ್ಪತ್ರೆಗೆ ಹೋಗಿ. ಇದು ವೈದ್ಯರಿಗೆ ಸಹಾಯ ಮಾಡುತ್ತದೆ.", "🏥")
        ),
        "heatstroke" to listOf(
            FirstAidStep(1, "Move to Cool Area", "ತಂಪಾದ ಸ್ಥಳಕ್ಕೆ ಕರೆದೊಯ್ಯಿರಿ",
                "Move the person to a cool shaded area or air-conditioned room immediately.",
                "ವ್ಯಕ್ತಿಯನ್ನು ತಕ್ಷಣ ತಂಪಾದ ಛಾಯೆಯ ಸ್ಥಳಕ್ಕೆ ಕರೆದೊಯ್ಯಿರಿ.", "🌳"),
            FirstAidStep(2, "Cool the Body", "ದೇಹ ತಂಪುಗೊಳಿಸಿ",
                "Remove excess clothing. Apply cool wet cloths to neck, armpits, and groin. Fan the person.",
                "ಬಟ್ಟೆ ತೆಗೆಯಿರಿ. ಕತ್ತು, ತೋಳಿನ ಕೆಳಗೆ ತಣ್ಣೀರು ಬಟ್ಟೆ ಹಾಕಿ.", "🧊"),
            FirstAidStep(3, "Give Water If Conscious", "ಎಚ್ಚರವಿದ್ದರೆ ನೀರು ಕೊಡಿ",
                "If fully conscious, give small sips of cool water. Never give water to unconscious person.",
                "ಸಂಪೂರ್ಣ ಎಚ್ಚರವಿದ್ದರೆ ತಣ್ಣೀರು ಸ್ವಲ್ಪ ಸ್ವಲ್ಪ ಕೊಡಿ. ಮೂರ್ಛೆ ಹೋದರೆ ನೀರು ಕೊಡಬೇಡಿ.", "💧"),
            FirstAidStep(4, "Monitor Temperature", "ತಾಪಮಾನ ಗಮನಿಸಿ",
                "Continue cooling until body temperature drops below 38°C or emergency services arrive.",
                "ತಾಪಮಾನ 38°C ಗಿಂತ ಕಡಿಮೆಯಾಗುವವರೆಗೆ ತಂಪುಗೊಳಿಸಿ.", "🌡️"),
            FirstAidStep(5, "Call 108", "108 ಕರೆ ಮಾಡಿ",
                "Heat stroke is a medical emergency. Always call 108 — even if the person seems to recover.",
                "ಶಾಖದ ಹೊಡೆತ ತುರ್ತು ಸ್ಥಿತಿ. ಚೇತರಿಸಿದರೂ 108 ಕರೆ ಮಾಡಿ.", "📞")
        ),
        "seizure" to listOf(
            FirstAidStep(1, "Keep Them Safe", "ಸುರಕ್ಷಿತವಾಗಿ ಇರಿಸಿ",
                "Clear the area of hard or sharp objects. Do not restrain the person — let the seizure run its course.",
                "ಗಟ್ಟಿ ವಸ್ತುಗಳನ್ನು ದೂರ ಮಾಡಿ. ವ್ಯಕ್ತಿಯನ್ನು ಹಿಡಿದಿಡಬೇಡಿ.", "🛡️"),
            FirstAidStep(2, "Cushion the Head", "ತಲೆ ಕೆಳಗೆ ಮೆತ್ತಗಿನದು ಇಡಿ",
                "Place something soft under their head. Remove glasses if worn. Loosen tight clothing around neck.",
                "ತಲೆ ಕೆಳಗೆ ಮೆತ್ತನೆಯ ವಸ್ತು ಇಡಿ. ಕತ್ತಿನ ಸುತ್ತ ಬಿಗಿ ಬಟ್ಟೆ ಸಡಿಲಿಸಿ.", "🛋️"),
            FirstAidStep(3, "Time the Seizure", "ಸೆಳೆತ ಸಮಯ ಗಮನಿಸಿ",
                "Note when the seizure started. If it lasts more than 5 minutes or person does not regain consciousness — call 108.",
                "ಸೆಳೆತ ಯಾವಾಗ ಶುರುವಾಯಿತು ಗಮನಿಸಿ. 5 ನಿಮಿಷಕ್ಕಿಂತ ಹೆಚ್ಚಾದರೆ 108 ಕರೆ ಮಾಡಿ.", "⏱️"),
            FirstAidStep(4, "Recovery Position", "ಚೇತರಿಕೆ ಸ್ಥಿತಿ",
                "After seizure ends, gently roll them onto their side (recovery position) to keep airway clear.",
                "ಸೆಳೆತ ನಿಂತ ನಂತರ ಉಸಿರಾಟ ಮಾರ್ಗ ತೆರೆದಿರಲು ಮಗ್ಗುಲಾಗಿ ಮಲಗಿಸಿ.", "🔄"),
            FirstAidStep(5, "Stay With Them", "ಜೊತೆಯಲ್ಲಿರಿ",
                "Stay with the person until fully conscious. Speak calmly and reassuringly as they recover.",
                "ಸಂಪೂರ್ಣ ಎಚ್ಚರವಾಗುವವರೆಗೆ ಜೊತೆಯಲ್ಲಿರಿ. ಶಾಂತವಾಗಿ ಮಾತಾಡಿ.", "🤝")
        )
    )

    val dosDonts = mapOf(
        "snakebite" to listOf(
            DosDontsItem("Keep the victim calm and still", "ಸಂತ್ರಸ್ತರನ್ನು ಶಾಂತವಾಗಿ ಇರಿಸಿ", true),
            DosDontsItem("Immobilise the bitten limb below heart level", "ಕಡಿದ ಅಂಗ ಹೃದಯ ಮಟ್ಟಕ್ಕಿಂತ ಕೆಳಗಿರಲಿ", true),
            DosDontsItem("Rush to hospital immediately", "ತಕ್ಷಣ ಆಸ್ಪತ್ರೆಗೆ ಹೋಗಿ", true),
            DosDontsItem("Remove tight accessories near bite", "ಕಡಿದ ಸ್ಥಳದ ಹತ್ತಿರ ಬಿಗಿ ವಸ್ತು ತೆಗೆಯಿರಿ", true),
            DosDontsItem("Do NOT suck out the venom", "ವಿಷ ಬಾಯಿಂದ ಹೀರಬೇಡಿ", false),
            DosDontsItem("Do NOT apply tourniquet", "ಬಟ್ಟೆ ಬಿಗಿಯಾಗಿ ಕಟ್ಟಬೇಡಿ", false),
            DosDontsItem("Do NOT cut or incise the wound", "ಕಡಿದ ಸ್ಥಳ ಕತ್ತರಿಸಬೇಡಿ", false),
            DosDontsItem("Do NOT give alcohol or traditional remedies", "ಮದ್ಯ ಅಥವಾ ಸಾಂಪ್ರದಾಯಿಕ ಚಿಕಿತ್ಸೆ ಕೊಡಬೇಡಿ", false)
        ),
        "burn" to listOf(
            DosDontsItem("Cool with running water for 10–20 minutes", "10-20 ನಿಮಿಷ ನೀರಿನಲ್ಲಿ ತಂಪುಗೊಳಿಸಿ", true),
            DosDontsItem("Cover loosely with clean material", "ಸ್ವಚ್ಛ ಬಟ್ಟೆಯಿಂದ ಸಡಿಲವಾಗಿ ಮುಚ್ಚಿ", true),
            DosDontsItem("Seek medical help for large/severe burns", "ದೊಡ್ಡ ಸುಟ್ಟಗಾಯಕ್ಕೆ ತಕ್ಷಣ ಆಸ್ಪತ್ರೆಗೆ ಹೋಗಿ", true),
            DosDontsItem("Do NOT apply toothpaste or oil", "ಟೂತ್‌ಪೇಸ್ಟ್ ಅಥವಾ ಎಣ್ಣೆ ಹಚ್ಚಬೇಡಿ", false),
            DosDontsItem("Do NOT use ice or ice water", "ಐಸ್ ಅಥವಾ ತುಂಬಾ ತಣ್ಣೀರು ಬಳಸಬೇಡಿ", false),
            DosDontsItem("Do NOT burst blisters", "ಗುಳ್ಳೆ ಒಡೆಯಬೇಡಿ", false),
            DosDontsItem("Do NOT use fluffy cotton wool directly", "ಹತ್ತಿ ನೇರ ಹಚ್ಚಬೇಡಿ", false)
        ),
        "choking" to listOf(
            DosDontsItem("Give 5 firm back blows between shoulder blades", "ಭುಜದ ಮಧ್ಯೆ 5 ಗಟ್ಟಿ ಏಟು ಕೊಡಿ", true),
            DosDontsItem("Perform Heimlich manoeuvre (abdominal thrusts)", "ಹೊಟ್ಟೆ ಒತ್ತಡ ನೀಡಿ", true),
            DosDontsItem("Call 108 immediately", "ತಕ್ಷಣ 108 ಕರೆ ಮಾಡಿ", true),
            DosDontsItem("Begin CPR if person becomes unconscious", "ಮೂರ್ಛೆ ಹೋದರೆ CPR ಮಾಡಿ", true),
            DosDontsItem("Do NOT do a blind finger sweep in mouth", "ಬೆರಳಿಂದ ಬಾಯಿ ಕೆದಕಬೇಡಿ", false),
            DosDontsItem("Do NOT leave the person alone", "ಒಂಟಿಯಾಗಿ ಬಿಡಬೇಡಿ", false),
            DosDontsItem("Do NOT shake or tilt the person violently", "ಒದ್ದಾಡಿಸಬೇಡಿ", false)
        ),
        "heartattack" to listOf(
            DosDontsItem("Call 108 immediately", "ತಕ್ಷಣ 108 ಕರೆ ಮಾಡಿ", true),
            DosDontsItem("Help the person sit or lie down comfortably", "ಆರಾಮದಾಯಕ ಸ್ಥಿತಿಯಲ್ಲಿ ಕೂರಿಸಿ", true),
            DosDontsItem("Give aspirin if not allergic and conscious", "ಅಲರ್ಜಿ ಇಲ್ಲದಿದ್ದರೆ ಆಸ್ಪಿರಿನ್ ಕೊಡಿ", true),
            DosDontsItem("Loosen tight clothing around neck and chest", "ಕತ್ತು ಮತ್ತು ಎದೆ ಸುತ್ತ ಬಿಗಿ ಬಟ್ಟೆ ಸಡಿಲಿಸಿ", true),
            DosDontsItem("Begin CPR if person stops breathing", "ಉಸಿರಾಟ ನಿಂತರೆ CPR ಪ್ರಾರಂಭಿಸಿ", true),
            DosDontsItem("Do NOT leave the person alone", "ವ್ಯಕ್ತಿಯನ್ನು ಒಂಟಿಯಾಗಿ ಬಿಡಬೇಡಿ", false),
            DosDontsItem("Do NOT give food or water", "ಆಹಾರ ಅಥವಾ ನೀರು ಕೊಡಬೇಡಿ", false),
            DosDontsItem("Do NOT let them walk or exert themselves", "ನಡೆಯಲು ಅಥವಾ ಶ್ರಮಪಡಲು ಬಿಡಬೇಡಿ", false),
            DosDontsItem("Do NOT ignore symptoms hoping they pass", "ಲಕ್ಷಣಗಳನ್ನು ನಿರ್ಲಕ್ಷಿಸಬೇಡಿ", false)
        ),
        "bleeding" to listOf(
            DosDontsItem("Apply firm direct pressure on the wound", "ಗಾಯದ ಮೇಲೆ ಗಟ್ಟಿ ಒತ್ತಡ ಹಾಕಿ", true),
            DosDontsItem("Elevate the wound above heart level", "ಗಾಯದ ಭಾಗ ಹೃದಯ ಮಟ್ಟಕ್ಕಿಂತ ಮೇಲೆ ಇರಿಸಿ", true),
            DosDontsItem("Use clean cloth or sterile bandage", "ಸ್ವಚ್ಛ ಬಟ್ಟೆ ಅಥವಾ ಬ್ಯಾಂಡೇಜ್ ಬಳಸಿ", true),
            DosDontsItem("Keep the person warm and calm", "ವ್ಯಕ್ತಿಯನ್ನು ಬೆಚ್ಚಗೆ ಮತ್ತು ಶಾಂತವಾಗಿ ಇರಿಸಿ", true),
            DosDontsItem("Call 108 for deep or large wounds", "ದೊಡ್ಡ ಗಾಯಕ್ಕೆ 108 ಕರೆ ಮಾಡಿ", true),
            DosDontsItem("Do NOT remove embedded objects from wound", "ಗಾಯದಲ್ಲಿ ಸಿಕ್ಕಿದ ವಸ್ತು ತೆಗೆಯಬೇಡಿ", false),
            DosDontsItem("Do NOT remove soaked cloth — add more on top", "ನೆನೆದ ಬಟ್ಟೆ ತೆಗೆಯಬೇಡಿ — ಮೇಲೆ ಇನ್ನೊಂದು ಹಾಕಿ", false),
            DosDontsItem("Do NOT use tourniquet unless trained", "ತರಬೇತಿ ಇಲ್ಲದಿದ್ದರೆ ಟೂರ್ನಿಕೆಟ್ ಬಳಸಬೇಡಿ", false),
            DosDontsItem("Do NOT attempt to stitch wound yourself", "ಗಾಯ ಹೊಲೆಯಲು ಪ್ರಯತ್ನಿಸಬೇಡಿ", false)
        ),
        "fracture" to listOf(
            DosDontsItem("Immobilise the limb in position found", "ಸಿಕ್ಕಿದ ಸ್ಥಿತಿಯಲ್ಲೇ ಅಂಗ ಸ್ಥಿರಗೊಳಿಸಿ", true),
            DosDontsItem("Apply ice wrapped in cloth to reduce swelling", "ಬಟ್ಟೆಯಲ್ಲಿ ಸುತ್ತಿದ ಐಸ್ ಹಾಕಿ", true),
            DosDontsItem("Keep the person still and calm", "ವ್ಯಕ್ತಿಯನ್ನು ಸ್ಥಿರವಾಗಿ ಮತ್ತು ಶಾಂತವಾಗಿ ಇರಿಸಿ", true),
            DosDontsItem("Seek medical help and X-ray", "ವೈದ್ಯರನ್ನು ಕಾಣಿ ಮತ್ತು X-ray ತೆಗೆಯಿರಿ", true),
            DosDontsItem("Do NOT try to straighten the bone", "ಮೂಳೆ ನೇರ ಮಾಡಲು ಪ್ರಯತ್ನಿಸಬೇಡಿ", false),
            DosDontsItem("Do NOT apply ice directly on skin", "ನೇರ ಐಸ್ ಚರ್ಮದ ಮೇಲೆ ಹಾಕಬೇಡಿ", false),
            DosDontsItem("Do NOT move person if spine injury suspected", "ಬೆನ್ನುಮೂಳೆ ಗಾಯ ಅನುಮಾನವಿದ್ದರೆ ಅಲ್ಲಾಡಿಸಬೇಡಿ", false),
            DosDontsItem("Do NOT give food or water before surgery", "ಶಸ್ತ್ರಚಿಕಿತ್ಸೆ ಮೊದಲು ಆಹಾರ ನೀರು ಕೊಡಬೇಡಿ", false)
        ),
        "drowning" to listOf(
            DosDontsItem("Throw rope or life ring — don't jump in unless trained", "ಹಗ್ಗ ಎಸೆಯಿರಿ — ತರಬೇತಿ ಇಲ್ಲದೆ ನೀರಿಗೆ ಹಾರಬೇಡಿ", true),
            DosDontsItem("Support head and neck when removing from water", "ನೀರಿನಿಂದ ತೆಗೆಯುವಾಗ ತಲೆ ಮತ್ತು ಕತ್ತು ಆಧರಿಸಿ", true),
            DosDontsItem("Begin CPR immediately if not breathing", "ಉಸಿರಾಟ ಇಲ್ಲದಿದ್ದರೆ ತಕ್ಷಣ CPR ಮಾಡಿ", true),
            DosDontsItem("Call 108 even if person seems recovered", "ಚೇತರಿಸಿದರೂ 108 ಕರೆ ಮಾಡಿ", true),
            DosDontsItem("Do NOT hold person upside down to drain water", "ನೀರು ತೆಗೆಯಲು ತಲೆಕೆಳಗಾಗಿ ಹಿಡಿಯಬೇಡಿ", false),
            DosDontsItem("Do NOT leave person alone after rescue", "ರಕ್ಷಿಸಿದ ನಂತರ ಒಂಟಿಯಾಗಿ ಬಿಡಬೇಡಿ", false),
            DosDontsItem("Do NOT give food or drink immediately", "ತಕ್ಷಣ ಆಹಾರ ಅಥವಾ ನೀರು ಕೊಡಬೇಡಿ", false)
        ),
        "poisoning" to listOf(
            DosDontsItem("Call 1800-116-117 or 108 immediately", "ತಕ್ಷಣ 1800-116-117 ಅಥವಾ 108 ಕರೆ ಮಾಡಿ", true),
            DosDontsItem("Keep the poison container for doctors", "ವೈದ್ಯರಿಗಾಗಿ ವಿಷದ ಡಬ್ಬಿ ಇಟ್ಟುಕೊಳ್ಳಿ", true),
            DosDontsItem("Rinse skin or eyes with water for 15-20 minutes if contacted", "ಸ್ಪರ್ಶವಾದರೆ 15-20 ನಿಮಿಷ ನೀರಿನಿಂದ ತೊಳೆಯಿರಿ", true),
            DosDontsItem("Move to fresh air if poison was inhaled", "ವಿಷ ಉಸಿರಾಡಿದ್ದರೆ ತಾಜಾ ಗಾಳಿಗೆ ಕರೆದೊಯ್ಯಿರಿ", true),
            DosDontsItem("Do NOT induce vomiting unless told by doctor", "ವೈದ್ಯರ ಸಲಹೆ ಇಲ್ಲದೆ ವಾಂತಿ ಮಾಡಿಸಬೇಡಿ", false),
            DosDontsItem("Do NOT give milk, water, or any liquid without advice", "ಸಲಹೆ ಇಲ್ಲದೆ ಹಾಲು, ನೀರು ಕೊಡಬೇಡಿ", false),
            DosDontsItem("Do NOT wait for symptoms to worsen", "ಲಕ್ಷಣ ಹೆಚ್ಚಾಗುವವರೆಗೆ ಕಾಯಬೇಡಿ", false),
            DosDontsItem("Do NOT try home remedies for poisoning", "ಮನೆ ಮದ್ದು ಪ್ರಯತ್ನಿಸಬೇಡಿ", false)
        ),
        "heatstroke" to listOf(
            DosDontsItem("Move person to cool shaded area immediately", "ತಕ್ಷಣ ತಂಪಾದ ಸ್ಥಳಕ್ಕೆ ಕರೆದೊಯ್ಯಿರಿ", true),
            DosDontsItem("Apply cool wet cloths to neck, armpits and groin", "ಕತ್ತು, ತೋಳಿನ ಕೆಳಗೆ ತಣ್ಣೀರು ಬಟ್ಟೆ ಹಾಕಿ", true),
            DosDontsItem("Fan the person to help cool down", "ಗಾಳಿ ಬೀಸಿ ತಂಪುಗೊಳಿಸಿ", true),
            DosDontsItem("Give small sips of cool water if conscious", "ಎಚ್ಚರವಿದ್ದರೆ ತಣ್ಣೀರು ಸ್ವಲ್ಪ ಸ್ವಲ್ಪ ಕೊಡಿ", true),
            DosDontsItem("Call 108 — heat stroke is life threatening", "108 ಕರೆ ಮಾಡಿ — ಶಾಖದ ಹೊಡೆತ ಅಪಾಯಕಾರಿ", true),
            DosDontsItem("Do NOT give water to unconscious person", "ಮೂರ್ಛೆ ಹೋದ ವ್ಯಕ್ತಿಗೆ ನೀರು ಕೊಡಬೇಡಿ", false),
            DosDontsItem("Do NOT use ice directly on skin", "ಚರ್ಮದ ಮೇಲೆ ನೇರ ಐಸ್ ಹಾಕಬೇಡಿ", false),
            DosDontsItem("Do NOT give alcohol or caffeine drinks", "ಮದ್ಯ ಅಥವಾ ಕಾಫಿ ಕೊಡಬೇಡಿ", false),
            DosDontsItem("Do NOT leave person in a hot car or closed room", "ಬಿಸಿ ಕಾರು ಅಥವಾ ಮುಚ್ಚಿದ ಕೋಣೆಯಲ್ಲಿ ಬಿಡಬೇಡಿ", false)
        ),
        "seizure" to listOf(
            DosDontsItem("Clear area of hard or sharp objects", "ಗಟ್ಟಿ ಮತ್ತು ಚೂಪಾದ ವಸ್ತುಗಳನ್ನು ದೂರ ಮಾಡಿ", true),
            DosDontsItem("Place something soft under their head", "ತಲೆ ಕೆಳಗೆ ಮೆತ್ತನೆಯ ವಸ್ತು ಇಡಿ", true),
            DosDontsItem("Time the seizure — call 108 if over 5 minutes", "ಸಮಯ ಗಮನಿಸಿ — 5 ನಿಮಿಷಕ್ಕಿಂತ ಹೆಚ್ಚಾದರೆ 108 ಕರೆ ಮಾಡಿ", true),
            DosDontsItem("Roll to recovery position after seizure ends", "ಸೆಳೆತ ನಿಂತ ನಂತರ ಮಗ್ಗುಲಾಗಿ ಮಲಗಿಸಿ", true),
            DosDontsItem("Stay with person until fully conscious", "ಸಂಪೂರ್ಣ ಎಚ್ಚರವಾಗುವವರೆಗೆ ಜೊತೆಯಲ್ಲಿರಿ", true),
            DosDontsItem("Do NOT restrain or hold down the person", "ವ್ಯಕ್ತಿಯನ್ನು ಹಿಡಿದಿಡಬೇಡಿ", false),
            DosDontsItem("Do NOT put anything in their mouth", "ಬಾಯಿಯಲ್ಲಿ ಯಾವುದನ್ನೂ ಹಾಕಬೇಡಿ", false),
            DosDontsItem("Do NOT give water during the seizure", "ಸೆಳೆತ ಸಮಯದಲ್ಲಿ ನೀರು ಕೊಡಬೇಡಿ", false),
            DosDontsItem("Do NOT leave person alone after seizure", "ಸೆಳೆತದ ನಂತರ ಒಂಟಿಯಾಗಿ ಬಿಡಬೇಡಿ", false)
        )
    )

    val hospitals = listOf(
        Hospital("Bowring & Lady Curzon Hospital", "Bengaluru Urban", "Shivajinagar, Bengaluru 560001", "080-22867676", "Government"),
        Hospital("Victoria Hospital", "Bengaluru Urban", "Kempe Gowda Rd, Bengaluru 560002", "080-22971600", "Government"),
        Hospital("Kidwai Memorial Institute", "Bengaluru Urban", "Dr. M H Marigowda Rd, Bengaluru", "080-26569045", "Government"),
        Hospital("NIMHANS", "Bengaluru Urban", "Hosur Rd, Bengaluru 560029", "080-46110007", "Government"),
        Hospital("St. John's Medical College Hospital", "Bengaluru Urban", "Sarjapur Rd, Bengaluru 560034", "080-22065000", "Private"),
        Hospital("Manipal Hospital Whitefield", "Bengaluru Urban", "Whitefield, Bengaluru 560066", "080-20913456", "Private"),
        Hospital("Mysuru District Hospital", "Mysuru", "Irwin Road, Mysuru 570021", "0821-2523025", "Government"),
        Hospital("Columbia Asia Hospital Mysore", "Mysuru", "Nazarbad, Mysuru 570010", "0821-3989898", "Private"),
        Hospital("Mangaluru Government Wenlock Hospital", "Dakshina Kannada", "Hampankatta, Mangaluru 575001", "0824-2423000", "Government"),
        Hospital("KMC Hospital Mangaluru", "Dakshina Kannada", "Ambedkar Circle, Mangaluru", "0824-2445858", "Private"),
        Hospital("Hubballi KIMS Hospital", "Dharwad", "Vidyanagar, Hubballi 580021", "0836-2375252", "Private"),
        Hospital("Belagavi District Hospital", "Belagavi", "Nehru Nagar, Belagavi 590001", "0831-2422441", "Government")
    )
}